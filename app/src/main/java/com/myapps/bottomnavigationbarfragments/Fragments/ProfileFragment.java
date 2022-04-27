package com.myapps.bottomnavigationbarfragments.Fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.myapps.bottomnavigationbarfragments.LedgerDOA.DataBaseHelper;
import com.myapps.bottomnavigationbarfragments.LedgerDOA.TransactionModel;
import com.myapps.bottomnavigationbarfragments.R;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private DataBaseHelper dataBaseHelper;
    private ArrayList<TransactionModel> userList;
    private Button download_statement;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
//        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
//        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");


        userList = new ArrayList<>();
        dataBaseHelper = new DataBaseHelper(requireActivity().getApplicationContext());
//        download_statement = view.findViewById(R.id.downloadStatement);



//        download_statement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//                    if (requireActivity().getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
//                        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                        requestPermissions(permissions, 1);
//                    } else {
//                        importData();
//                    }
//                } else {
//                    importData();
//                }
//
//            }
//        });


        return view;
    }


//    private void importData() {
//
//
//        userList = dataBaseHelper.getAllLocalUser();
//
//        if (userList.size() > 0) {
//            createXlFile();
//        } else {
//            Toast.makeText(requireActivity().getApplicationContext(), "No records about expenses", Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void createXlFile() {
//        Workbook wb = new HSSFWorkbook();
//
//        Cell cell = null;
//
//        Sheet sheet = null;
//        sheet = wb.createSheet("Demo Excel Sheet");
//        Row row = sheet.createRow(0);
//
//        cell = row.createCell(0);
//        cell.setCellValue("ID");
//
//
//        cell = row.createCell(1);
//        cell.setCellValue("Amount");
//
//
//        cell = row.createCell(2);
//        cell.setCellValue("Description");
//
//        cell = row.createCell(3);
//        cell.setCellValue("Category");
//
//
//        cell = row.createCell(4);
//        cell.setCellValue("Type");
//
//
//        cell = row.createCell(5);
//        cell.setCellValue("Date");
//
//        cell = row.createCell(6);
//        cell.setCellValue("Time");
//
//        sheet.setColumnWidth(0, (30 * 200));
//        sheet.setColumnWidth(1, (30 * 200));
//        sheet.setColumnWidth(2, (30 * 200));
//        sheet.setColumnWidth(3, (30 * 200));
//        sheet.setColumnWidth(4, (30 * 200));
//        sheet.setColumnWidth(5, (30 * 200));
//        sheet.setColumnWidth(6, (30 * 200));
//
//        for (int i = 0; i < userList.size(); i++) {
//            Row row1 = sheet.createRow(i + 1);
//
//            cell = row1.createCell(0);
//            cell.setCellValue(userList.get(i).getId());
//
//            cell = row1.createCell(1);
//            cell.setCellValue((userList.get(i).getAmount()));
//            //  cell.setCellStyle(cellStyle);
//
//            cell = row1.createCell(2);
//            cell.setCellValue(userList.get(i).getDescription());
//
//            cell = row1.createCell(3);
//            cell.setCellValue(userList.get(i).getCategory());
//
//            cell = row1.createCell(4);
//            cell.setCellValue((userList.get(i).getType()));
//            //  cell.setCellStyle(cellStyle);
//
//            cell = row1.createCell(5);
//            cell.setCellValue(userList.get(i).getDate());
//
//            cell = row1.createCell(6);
//            cell.setCellValue(userList.get(i).getTime());
//
//
//            sheet.setColumnWidth(0, (30 * 200));
//            sheet.setColumnWidth(1, (30 * 200));
//            sheet.setColumnWidth(2, (30 * 200));
//            sheet.setColumnWidth(3, (30 * 200));
//            sheet.setColumnWidth(4, (30 * 200));
//            sheet.setColumnWidth(5, (30 * 200));
//            sheet.setColumnWidth(6, (30 * 200));
//        }
//
//
//        String folderName = "Import Excel";
//        String fileName = folderName + System.currentTimeMillis() + ".xls";
//        String path = Environment.getExternalStorageDirectory() + File.separator + folderName + File.separator + fileName;
//
//        File file = new File(Environment.getExternalStorageDirectory() + File.separator + folderName);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//
//        FileOutputStream outputStream = null;
//
//
//        try {
//            outputStream = new FileOutputStream(path);
//            wb.write(outputStream);
//            Toast.makeText(requireActivity().getApplicationContext(), "Statement Created in " + path, Toast.LENGTH_SHORT).show();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(requireActivity().getApplicationContext(), "Failed Creating Statement", Toast.LENGTH_SHORT).show();
//            try {
//                outputStream.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//
//            }
//        }
//
//
//    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            importData();
//        } else {
//            Toast.makeText(requireActivity().getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
//        }
//    }

}


