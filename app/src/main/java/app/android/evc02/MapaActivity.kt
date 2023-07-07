package app.android.evc02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        map = p0
        val usil = LatLng(-12.07342340511936, -76.95220546479703)
        val upc = LatLng(-12.08728333259832, -77.04986700341395)
        val esan = LatLng(-12.104754985254411, -76.96160664759562)
        map.addMarker(MarkerOptions().position(usil).title("USIL, Universidad San Ignacio de Loyola"))
        map.addMarker(MarkerOptions().position(upc).title("UPC, Universidad Peruana de Ciencias Aplicadas"))
        map.addMarker(MarkerOptions().position(esan).title("ESAN, Escuela de Sanidad Naval"))
        val boundBuilder = LatLngBounds.builder()
            .include(usil)
            .include(upc)
            .include(esan)
        map.animateCamera(CameraUpdateFactory.newLatLngBounds(boundBuilder.build(), 150))
    }
}