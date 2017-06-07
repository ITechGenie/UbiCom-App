package com.itechgenie.apps.ubicomclient;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itechgenie.apps.ubicomclient.dto.RoomDTO;
import com.itechgenie.apps.ubicomclient.utils.ITGConstants;
import com.itechgenie.apps.ubicomclient.utils.RoomsAsyncLoader;

import static com.itechgenie.apps.ubicomclient.R.id.numberPickerLabelId;
import static com.itechgenie.apps.ubicomclient.R.id.saveButtonId;

public class RoomsActivity extends AppCompatActivity implements RoomsAsyncLoader.callBack {

    final static String LOGGER_TAG = "RoomsActivity";

    private EditText tempSelector;
    private Button saveBtn;
    private Button vacateBtn;
    private Button bookBtn;
    private TextView tempPickerLabelId;
    private TextView roomNoTxtId;
    private Spinner colorSpinner;
    int ageStart = 18;

    private TextView welcomeMsg ;

    private View roomTempLayout;
    private View roomColorLayout;

    private RoomDTO currentRoomDTO = null;

    private String emailID = null;

    private Boolean isBooked = false;

    private Boolean loadHomePage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        tempSelector = (EditText) findViewById(R.id.roomTempEditorId);
        saveBtn = (Button) findViewById(saveButtonId);
        vacateBtn = (Button) findViewById(R.id.vacateRoom);
        tempPickerLabelId = (TextView) findViewById(R.id.numberPickerLabelId);
        roomNoTxtId = (TextView) findViewById(R.id.roomNoTextId);
        colorSpinner = (Spinner) findViewById(R.id.roomColorSpinnerId);
        bookBtn = (Button) findViewById(R.id.bookMyRoom);

        roomTempLayout = (View) findViewById(R.id.roomTempLayoutId);
        roomColorLayout = (View) findViewById(R.id.roomColorLayoutId);

        welcomeMsg = (TextView) findViewById(R.id.welcomeMsgHolderId);

        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            currentRoomDTO = (RoomDTO) b.getSerializable(ITGConstants.ROOM_INFORMATION);
            emailID = (String) b.getSerializable("USER_EMAIL_ID");
            isBooked = (Boolean) b.getSerializable("IS_BOOKED");
            if (isBooked == null)
                isBooked = false;
        }

        Log.d("RoomsActivity", "Room obtained: " + currentRoomDTO);

        if (currentRoomDTO != null) {

            String roo = String.valueOf(currentRoomDTO.getRoomNo());
            Log.d("RoomsActivity", "Room no: " + roo);
            roomNoTxtId.setText(roo);

            String temp = String.valueOf(currentRoomDTO.getRoomTemp());
            Log.d("RoomsActivity", "Temperature: " + temp);
            tempSelector.setText(temp);

            int spnid = getIndex(currentRoomDTO.getRoomColor());
            Log.d("RoomsActivity", "Color index: " + spnid + " for " + currentRoomDTO.getRoomColor());
            colorSpinner.setSelection(spnid);

            if (isBooked) {
                vacateBtn.setVisibility(View.GONE);
                bookBtn.setVisibility(View.VISIBLE);
                saveBtn.setVisibility(View.GONE);
                welcomeMsg.setText("Room available for booking ");

                roomTempLayout.setVisibility(View.GONE);
                roomColorLayout.setVisibility(View.GONE);

            } else {
                saveBtn.setVisibility(View.VISIBLE);
                vacateBtn.setVisibility(View.VISIBLE);
                bookBtn.setVisibility(View.GONE);

                roomTempLayout.setVisibility(View.VISIBLE);
                roomColorLayout.setVisibility(View.VISIBLE);

                View laView = (View) findViewById(R.id.clientAppRoomLayoutId);
                laView.setBackgroundColor(getColorInt(currentRoomDTO.getRoomColor()));

                welcomeMsg.setText("My Room ");
            }
        }
    }

    private Integer getIndex(String color) {
        String[] colorArray = getResources().getStringArray(R.array.colorList);
        int i = 0;
        for (String s : colorArray) {
            if (s.equalsIgnoreCase(color))
                return i;
            else
                i++ ;
        }
        return 0;
    }

    public void onClick(View view) {
        Log.d(LOGGER_TAG, "Onclick is called: " + view.getId());
        loadHomePage = false ;
        if (view == tempSelector) {
            showAgeSelector(view);
        } else if (view == saveBtn ) {
            saveDetails(view);
        } else if (view == vacateBtn) {
            vacateRoom(view);
        } else if (view == bookBtn) {
            bookRoom(view);
        }
    }

    public void vacateRoom(View view) {
        loadHomePage = true ;
        RoomDTO roomDTON = new RoomDTO();
        roomDTON.setRoomNo(currentRoomDTO.getRoomNo());
        roomDTON.setUserEmail("DUMMY");
        new RoomsAsyncLoader(RoomsActivity.this).execute(roomDTON);

    }

    public void bookRoom(View view) {
        Log.d(LOGGER_TAG, "Book room clicked: " + view.getId());
        loadHomePage = true ;
        RoomDTO roomDTON = new RoomDTO();
        roomDTON.setUserEmail(emailID);
        roomDTON.setRoomNo(currentRoomDTO.getRoomNo());
        new RoomsAsyncLoader(RoomsActivity.this).execute(roomDTON);

    }

    public void saveDetails(View view) {
        Log.d(LOGGER_TAG, "Save details clicked: " + view.getId());
        Log.d(LOGGER_TAG, "Temperature Set: " + tempSelector.getText());
        Log.d(LOGGER_TAG, "Color Selected: " + colorSpinner.getSelectedItem());

        RoomDTO roomDTON = new RoomDTO();

        String color = (String) colorSpinner.getSelectedItem() ;
        // Set the selected values
        roomDTON.setRoomColor(color);
        roomDTON.setRoomTemp(tempSelector.getText().toString());
        roomDTON.setUserEmail(null);
        roomDTON.setRoomNo(currentRoomDTO.getRoomNo());

        View laView = (View) findViewById(R.id.clientAppRoomLayoutId);
        laView.setBackgroundColor(getColorInt(color));

        // Save booking details, Pass room roomDTO as parameter
        new RoomsAsyncLoader(RoomsActivity.this).execute(roomDTON);

    }

    public void showAgeSelector(final View view) {

        final Dialog d = new Dialog(RoomsActivity.this);
        d.setTitle(R.string.txt_temperature);
        d.setContentView(R.layout.number_picker_dialog);
        Button set = (Button) d.findViewById(R.id.search_btn_ok);
        Button cancel = (Button) d.findViewById(R.id.search_btn_cancel);
        final NumberPicker nopicker = (NumberPicker) d.findViewById(R.id.numberPickerId);


        TextView pickerLbl = (TextView) d.findViewById(numberPickerLabelId);
        pickerLbl.setText(R.string.txt_temperature);

        nopicker.setMaxValue(ageStart + 12);
        nopicker.setMinValue(ageStart);
        nopicker.setWrapSelectorWheel(false);
        nopicker.setValue(ageStart + 4);
        nopicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) view).setText(String.valueOf(nopicker.getValue()));
                d.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();
            }
        });
        d.show();
    }

    @Override
    public void returnText(Object value) {
        Log.d(LOGGER_TAG, "Return text in returnText: " + value);
        if (loadHomePage) {
            Toast.makeText(this, "Loading home page !",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private int getColorInt(String colorText) {

        int colorCode = Color.LTGRAY;

        if ("RED".equalsIgnoreCase(colorText)) {
            colorCode = Color.RED;
        }
        if ("GREEN".equalsIgnoreCase(colorText)) {
            colorCode = Color.GREEN;
        }
        if ("BLUE".equalsIgnoreCase(colorText)) {
            colorCode = Color.BLUE;
        }
        if ("YELLOW".equalsIgnoreCase(colorText)) {
            colorCode = Color.YELLOW;
        }
        if ("MAGENTA".equalsIgnoreCase(colorText)) {
            colorCode = Color.MAGENTA;
        }
        if ("CYAN".equalsIgnoreCase(colorText)) {
            colorCode = Color.CYAN;
        }

        return colorCode;

    }

}
