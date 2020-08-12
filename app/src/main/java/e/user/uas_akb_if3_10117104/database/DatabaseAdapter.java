package e.user.uas_akb_if3_10117104.database;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import e.user.uas_akb_if3_10117104.R;
import e.user.uas_akb_if3_10117104.model.TempatWisata;


/*
    tgl pengerjaan  : 11-Mei-2020
    Nim             : 10117104
    Nama            : Aditya Suheryana
    Kelas           : IF-3 / AKB-3
 */
public class DatabaseAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<TempatWisata> items;
    Context context;

    public DatabaseAdapter(Activity activity, List<TempatWisata> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row,null);

            TextView id = (TextView) convertView.findViewById(R.id.id);
            TextView nama = (TextView) convertView.findViewById(R.id.tv_item_name);
            TextView harga = (TextView) convertView.findViewById(R.id.tv_item_harga);
            ImageView foto = (ImageView) convertView.findViewById(R.id.img_item_photo);

            TempatWisata data = items.get(position);
            String img = data.getFoto();
            id.setText(data.getId());
            nama.setText(data.getNama());
            harga.setText(data.getHarga_tiket());

            /*String img = data.getId();
            int resId = ((Activity)context).getResources().getIdentifier(
                img,"drawable",((Activity)context).getPackageName()
            );*/
        return convertView;
    }
}
