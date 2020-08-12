package e.user.uas_akb_if3_10117104;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import e.user.uas_akb_if3_10117104.model.TempatWisata;


/*
    tgl pengerjaan  : 11-Mei-2020
    Nim             : 10117104
    Nama            : Aditya Suheryana
    Kelas           : IF-3 / AKB-3
 */
public class TempatWisataAdapter extends RecyclerView.Adapter<TempatWisataAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TempatWisata> arrayList;

    public TempatWisataAdapter(Context context, ArrayList<TempatWisata> arrayList){
        this.context = context;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public TempatWisataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new TempatWisataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempatWisataAdapter.ViewHolder holder, int position) {
        TempatWisata tw =  arrayList.get(position);
        String nama = tw.getNama();
        String harga = tw.getHarga_tiket();
        String foto = tw.getFoto();


        holder.tv_nama.setText(nama);
        holder.tv_deskripsi.setText(harga);
        holder.image.setImageURI(Uri.parse(foto));
        Picasso.with(null).load(tw.getFoto()).into(holder.image);
        holder.btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.btnMap.getContext(), "Ok kita lihat wisata "+holder.tv_nama.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_nama,tv_deskripsi;
        ImageView image;
        Button btnMap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_item_name);
            tv_deskripsi = itemView.findViewById(R.id.tv_item_harga);
            image = itemView.findViewById(R.id.img_item_photo);
            btnMap = itemView.findViewById(R.id.btn_view_map);

        }
    }

/*
    public TempatWisataAdapter(ArrayList namaList, ArrayList hargaList, ArrayList fotoList) {
        this.namaList = namaList;
        this.hargaList = hargaList;
        this.fotoList = fotoList;
    }

    @NonNull
    @Override
    public TempatWisataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        return new TempatWisataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempatWisataAdapter.ViewHolder holder, int position ) {

        TempatWisata tw =  arrayList.get(Position);
        final String Nama = String.valueOf(namaList.get(position));//Mengambil data (Nama) sesuai dengan posisi yang telah ditentukan
        final String harga = String.valueOf(hargaList.get(position));//Mengambil data (Jurusan) sesuai dengan posisi yang telah ditentukan
        holder.tv_nama.setText(Nama);
        holder.tv_deskripsi.setText(harga);
        Picasso.with(null).load().into(holder.image_trip);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama,tv_deskripsi;
        ImageView image;
        Button btnMap;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_item_name);
            tv_deskripsi = itemView.findViewById(R.id.tv_item_harga);
            image = itemView.findViewById(R.id.img_item_photo);
            btnMap = itemView.findViewById(R.id.btn_view_map);
        }
    }

 */
}
