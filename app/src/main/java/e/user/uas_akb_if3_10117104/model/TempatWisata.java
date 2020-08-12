package e.user.uas_akb_if3_10117104.model;

public class TempatWisata {
    private String id,nama,harga_tiket,foto,lat,lng,deskripsi;


    public TempatWisata(String id, String nama, String harga_tiket, String foto, String lat, String lng,String deskripsi) {
        this.id = id;
        this.deskripsi = deskripsi;
        this.nama = nama;
        this.harga_tiket = harga_tiket;
        this.foto = foto;
        this.lat = lat;
        this.lng = lng;
    }

    public TempatWisata() {
    }
    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga_tiket() {
        return harga_tiket;
    }

    public void setHarga_tiket(String harga_tiket) {
        this.harga_tiket = harga_tiket;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
