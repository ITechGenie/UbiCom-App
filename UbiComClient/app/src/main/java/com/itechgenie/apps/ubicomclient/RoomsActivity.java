package com.itechgenie.apps.ubicomclient;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.itechgenie.apps.ubicomclient.dto.RoomDTO;
import com.itechgenie.apps.ubicomclient.utils.ITGConstants;
import com.itechgenie.apps.ubicomclient.utils.RoomsAsyncLoader;

import static com.itechgenie.apps.ubicomclient.R.id.numberPickerLabelId;

public class RoomsActivity extends AppCompatActivity  implements RoomsAsyncLoader.callBack {

    final static String LOGGER_TAG = "RoomsActivity";

    private EditText tempSelector;
    private Button saveBtn;
    private Button vacateBtn;
    private TextView tempPickerLabelId ;
    private TextView roomNoTxtId ;
    private Spinner colorSpinner ;
    int ageStart = 18 ;

    private  RoomDTO currentRoomDTO = null ;

    private String emailID = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        tempSelector = (EditText) findViewById(R.id.roomTempEditorId);
        saveBtn = (Button)  findViewById(R.id.saveButtonId);
        vacateBtn = (Button)  findViewById(R.id.vacateRoom);
        tempPickerLabelId = (TextView) findViewById(R.id.numberPickerLabelId);
        roomNoTxtId = (TextView) findViewById(R.id.roomNoTextId);
        colorSpinner = (Spinner) findViewById(R.id.roomColorSpinnerId) ;



        Bundle b = this.getIntent().getExtras();
        if (b != null) {
            currentRoomDTO = (RoomDTO) b.getSerializable(ITGConstants.ROOM_INFORMATION);
            emailID = (String) b.getSerializable("USER_EMAIL_ID") ;
        }

        Log.d("RoomsActivity", "Room obtained: " + currentRoomDTO ) ;

        if (currentRoomDTO !=  null ) {

            String roo = String.valueOf(currentRoomDTO.getRoomNo()) ;
            Log.d("RoomsActivity", "Room no: " + roo ) ;
            roomNoTxtId.setText(roo);

            String temp = String.valueOf(currentRoomDTO.getRoomTemp()) ;
            Log.d("RoomsActivity", "Temperature: " + temp ) ;
            tempSelector.setText(temp);

            int spnid = getIndex(currentRoomDTO.getRoomColor()) ;
            Log.d("RoomsActivity", "Color index: " + spnid + " for " + currentRoomDTO.getRoomColor() ) ;
            colorSpinner.setSelection(spnid);
        }
    }

    private Integer getIndex(String color) {
        String[] colorArray = getResources().getStringArray(R.array.colorList) ;
        int i = 0 ;
        for (String s: colorArray) {
            if (s.equalsIgnoreCase(color))
               return i++ ;
        }
        return 0;
    }

    public void onClick(View view) {
        Log.d(LOGGER_TAG, "Onclick is called: " + view.getId());
        if (view == tempSelector) {
            showAgeSelector(view);
        } else if (view == saveBtn) {
            saveDetails(view);
        } else if (view == vacateBtn) {
            vacateRoom(view);
        }
    }

    public void vacateRoom(View view) {

        RoomDTO roomDTON = new RoomDTO()  ;
        roomDTON.setRoomNo(currentRoomDTO.getRoomNo());
        roomDTON.setUserEmail("DUMMY");
        new RoomsAsyncLoader(RoomsActivity.this).execute(roomDTON);

    }


    public void saveDetails(View view) {
        Log.d(LOGGER_TAG, "Save details clicked: " + view.getId()) ;
        Log.d(LOGGER_TAG, "Temperature Set: " + tempSelector.getText()) ;
        Log.d(LOGGER_TAG, "Color Selected: " + colorSpinner.getSelectedItem()) ;

        RoomDTO roomDTON = new RoomDTO()  ;

        // Set the selected values
        roomDTON.setRoomColor((String) colorSpinner.getSelectedItem());
        roomDTON.setRoomTemp (tempSelector.getText().toString());
        roomDTON.setIsAvailable("N");
        roomDTON.setUserEmail(emailID);
        roomDTON.setRoomNo(currentRoomDTO.getRoomNo());

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
        Log.d(LOGGER_TAG, "Return text: " + value) ;
    }
}
