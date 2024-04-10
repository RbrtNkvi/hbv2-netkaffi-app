package hi.netkaffi.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hi.netkaffi.databinding.ActivityQrTestBinding
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import hi.netkaffi.R


class QRActivity: AppCompatActivity(){
    private lateinit var binding: ActivityQrTestBinding
    private lateinit var  qrScanner: CodeScanner
    private val  cameraPermission = 1111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Toast.makeText(this, "Tester_2", Toast.LENGTH_LONG).show()
        binding = ActivityQrTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
