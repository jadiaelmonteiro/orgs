package study.jadiael.orgs

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        val view = TextView(this);
        view.setText("Jadiael Bonitão, Hello World!");
        setContentView(view);
    }

}