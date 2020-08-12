package e.user.uas_akb_if3_10117104;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


/*
    tgl pengerjaan  : 11-Mei-2020
    Nim             : 10117104
    Nama            : Aditya Suheryana
    Kelas           : IF-3 / AKB-3
 */
public class HomeFragment extends Fragment{

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView imageView1 = view.findViewById(R.id.imageview1);
        ImageView imageView2 = view.findViewById(R.id.imageview2);
        ImageView imageView3 = view.findViewById(R.id.imageview3);


        Picasso.with(getContext().getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/kemah-a20bb." +
                "appspot.com/o/Curug-parigi-traveling-yuk.jpg?alt=media&token=d17ed453-b9e3-47af-9aea-d0219a2f0a2f").into(imageView1);
        Picasso.with(getContext().getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/kemah-a20bb." +
                "appspot.com/o/Raja-Ampat-Papua-CekAja-2048x1076.jpg?alt=media&token=ce52432c-223d-4b9c-8e06-dfc269d3baa4").into(imageView2);
        Picasso.with(getContext().getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/kemah-a20bb.appspot.com" +
                "/o/jam_gadang_10820.jpg?alt=media&token=38403b9b-f312-42fa-afdf-44ee70a199c2").into(imageView3);

        return view;
    }

}