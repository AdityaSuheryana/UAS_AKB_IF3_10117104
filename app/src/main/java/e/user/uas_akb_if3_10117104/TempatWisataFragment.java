package e.user.uas_akb_if3_10117104;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Icon;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import e.user.uas_akb_if3_10117104.database.DatabaseAdapter;
import e.user.uas_akb_if3_10117104.database.DatabaseHelper;
import e.user.uas_akb_if3_10117104.model.TempatWisata;
import e.user.uas_akb_if3_10117104.presenter.FormVP;

import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import static e.user.uas_akb_if3_10117104.R.string.user_location_permission_explanation;

/*
    tgl pengerjaan  : 11-Agustus-2020
    Nim             : 10117104
    Nama            : Aditya Suheryana
    Kelas           : IF-3 / AKB-3
 */
public class TempatWisataFragment extends Fragment implements OnMapReadyCallback,PermissionsListener {

    ListView listView;
    private FormVP.Presenter presenter;
    private TextView textView;
    List<TempatWisata> arrayList = new ArrayList<TempatWisata>();
    DatabaseAdapter adapter;
    TempatWisataAdapter adapt;
    AlertDialog.Builder dialog;
    DatabaseHelper SQLite = new DatabaseHelper(getActivity());
    private MapView mapView;

    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";
    public static final String TAG_HARGA = "harga";
    public static final String TAG_LAT = "lat";
    public static final String TAG_LNG = "lng";
    public static final String TAG_FOTO = "foto";
    public static final String TAG_DESK = "deskripsi";

    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Mapbox.getInstance(getContext().getApplicationContext(), getString(R.string.mapbox_access_token));

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        IconFactory iconFactory = IconFactory.getInstance(getContext());
        Icon icon = iconFactory.fromResource(R.drawable.map_marker);

        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                TempatWisataFragment.this.mapboxMap = mapboxMap;

                mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41"), new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        enableLocation(style);
                    }
                });
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(-6.873109, 107.619465))
                        .title("Kota Bandung").icon(icon));
            }
        });
        SQLite = new DatabaseHelper(getActivity());
        listView = (ListView) view.findViewById(R.id.list_view);
        adapter = new DatabaseAdapter(getActivity(), arrayList);
        listView.setAdapter(adapter);

        getAllData();
        return view;
    }

    @SuppressWarnings( {"MissingPermission"})
    private void enableLocation(@NonNull Style style) {
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

// Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

// Activate with options
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(getContext(), style).build());

// Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

// Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

// Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);
        } else {
            permissionsManager = new PermissionsManager((PermissionsListener) TempatWisataFragment.this);
            permissionsManager.requestLocationPermissions((Activity) getContext());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getContext(), user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocation(style);
                }
            });
        } else {
            Toast.makeText(getContext(), R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();

        }
    }
    private void getAllData() {
        ArrayList<HashMap<String, String>> row = SQLite.getAllData();

        for (int i = 0; i < row.size(); i++){
            String id = row.get(i).get(TAG_ID);
            String nama = row.get(i).get(TAG_NAMA);
            String harga = row.get(i).get(TAG_HARGA);
            String lat = row.get(i).get(TAG_LAT);
            String lng = row.get(i).get(TAG_LNG);
            String foto = row.get(i).get(TAG_FOTO);
            String desk = row.get(i).get(TAG_DESK);

            TempatWisata data = new TempatWisata();

            data.setId(id);
            data.setNama(nama);
            data.setHarga_tiket(harga);
            data.setLat(lat);
            data.setLng(lng);
            data.setFoto(foto);
            data.setDeskripsi(desk);

            arrayList.add(data);
        }
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        mapboxMap.setStyle(new Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41"));
    }
}