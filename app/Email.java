import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.database.R;
import com.example.database.User;

import java.util.List;

public class Email extends ArrayAdapter<User> {
    private Activity context;
    private List<User> emaillist;

    public Email(Activity context, List<User> emaillist){
        super(context, R.layout.activity_main3, emaillist);
        this.context=context;
        this.emaillist=emaillist;
    }

}