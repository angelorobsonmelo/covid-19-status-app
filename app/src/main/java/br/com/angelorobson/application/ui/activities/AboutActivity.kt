package br.com.angelorobson.application.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.angelorobson.covid19.R
import com.vansuita.materialabout.builder.AboutBuilder
import kotlinx.android.synthetic.main.about_activity.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)


        actionbar?.title = getString(R.string.about)
        setUpAboutScreen()
    }

    private fun setUpAboutScreen() {
        val frameLayout = about

        val builder = AboutBuilder.with(this)
            .setAppIcon(R.mipmap.ic_launcher)
            .setAppName(R.string.app_name)
            .setPhoto(R.drawable.angelo)
            .setCover(R.mipmap.profile_cover)
            .setLinksAnimated(true)
            .setDividerDashGap(13)
            .setName("Ângelo Robson")
            .setSubTitle("Engenheiro de Software")
            .setLinksColumnsCount(4)
            .setBrief("Sou um amante e um entusiasta da tecnologia. Fabricante de idéias e curioso.")
            .addGitHubLink("angelorobsonmelo")
            .addLinkedInLink("ângelo-melo-8b4a47148/")
            .addEmailLink("angelorobsonmelo@gmail.com")
            .addWhatsappLink("Ângelo Robson", "+5582991228122")
            .setVersionNameAsAppSubTitle()
            .addShareAction(R.string.app_name)
            .setActionsColumnsCount(2)
            .setWrapScrollView(true)
            .setShowAsCard(true)

        val view = builder.build()

        frameLayout.addView(view)

        val item = builder.lastLinkId

        view.findItem(item).setOnClickListener {
            val url =
                "https://api.whatsapp.com/send?phone=5582991228122&text=Ol%C3%A1%2C%20encontrei%20voc%C3%AA%20pelo%20aplicativo%20Covid-19%20Report"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
