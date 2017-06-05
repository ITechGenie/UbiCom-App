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

public class RoomsActivity extends AppCompatActivity  {

    final static String LOGGER_TAG = "RoomsActivity";

    private EditText tempSelector;
    private Button saveBtn;
    private TextView tempPickerLabelId ;
    private TextView roomNoTxtId ;
    private Spinner colorSpinner ;
    int ageStart = 18 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);

        tempSelector = (EditText) findViewById(R.id.roomTempEditorId);
        saveBtn = (Button)  findViewById(R.id.saveButtonId);
        tempPickerLabelId = (TextView) findViewById(R.id.numberPickerLabelId);
        roomNoTxtId = (TextView) findViewById(R.id.roomNoTextId);
        colorSpinner = (Spinner) findViewById(R.id.roomColorSpinnerId) ;

        RoomDTO roomDTO = null ;

        Bundle b = this.getIntent().getExtras();
        if (b != null)
            roomDTO = (RoomDTO) b.getSerializable(ITGConstants.ROOM_INFORMATION);

        Log.d("RoomsActivity", "Room obtained: " + roomDTO ) ;

        if (roomDTO !=  null ) {

            String roo = String.valueOf(roomDTO.getRoomNo()) ;
            Log.d("RoomsActivity", "Room no: " + roo ) ;
            roomNoTxtId.setText(roo);

            String temp = String.valueOf(roomDTO.getRoomTemp()) ;
            Log.d("RoomsActivity", "Temperature: " + roo ) ;
            tempSelector.setText(temp);

            int spnid = getIndex(roomDTO.getRoomColor()) ;
            Log.d("RoomsActivity", "Color index: " + spnid + " for " + roomDTO.getRoomColor() ) ;
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
        }
    }

    public void saveDetails(View view) {
        Log.d(LOGGER_TAG, "Save details clicked: " + view.getId()) ;
        Log.d(LOGGER_TAG, "Temperature Set: " + tempSelector.getText()) ;
        Log.d(LOGGER_TAG, "Color Selected: " + colorSpinner.getSelectedItem()) ;

        RoomDTO roomDTO = null ;
        Bundle b = this.getIntent().getExtras();
        if (b != null)
            roomDTO = (RoomDTO) b.getSerializable(ITGConstants.ROOM_INFORMATION);

        // Set the selected values
        roomDTO.setRoomColor((String) colorSpinner.getSelectedItem());
        roomDTO.setRoomTemp (tempSelector.getText().toString());
        roomDTO.setIsAvailable("N");
        roomDTO.setUserEmail((String) b.getSerializable("USER_EMAIL_ID"));

        // Save booking details, Pass room roomDTO as parameter
        new RoomsAsyncLoader(RoomsActivity.this).execute(roomDTO);

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
}
