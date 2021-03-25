package pollub.ism.lab04;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Kolko_krzyzyk
{
    Button[] przycisk;
    Activity aktywnosc;
    static int ruchy = 0;
    char[] arr = {'O', 'X'};
    boolean jestWygrany = false;
    boolean pustePole = false;

    public Kolko_krzyzyk(Activity activity)
    {
        this.aktywnosc = activity;
        this.przycisk = new Button[9];
        for (int i = 1; i <= 9; i++)
        {
            int id = this.aktywnosc.getResources().getIdentifier("button" + i, "id", this.aktywnosc.getPackageName());
            przycisk[i - 1] = activity.findViewById(id);
        }
    }

    public char[] sprawdzPrzycisk()
    {
        char[] getBoardSigns = new char[9];
        for (int i = 1; i <= 9; i++)
        {
            int id = this.aktywnosc.getResources().getIdentifier("button" + i, "id", this.aktywnosc.getPackageName());
            przycisk[i - 1] = (Button) this.aktywnosc.findViewById(id);
            if (!przycisk[i - 1].getText().equals(""))
                getBoardSigns[i - 1] = przycisk[i - 1].getText().charAt(0);
        }
        return getBoardSigns;
    }

    public void rysowanie(View view, int czyjRuch)
    {
        Button button1 = this.aktywnosc.findViewById(view.getId());
        button1.setEnabled(false);
        button1.setText(String.valueOf(this.arr[czyjRuch]));
    }

    public void czyWygrana()
    {
        for (int i = 0; i < 3; i++)
        {
            if (!przycisk[i].getText().equals(""))
            {
                if (przycisk[i].getText().equals(przycisk[i + 3].getText()) && przycisk[i + 3].getText().equals(przycisk[i + 6].getText()))
                {
                    jestWygrany = true;
                    break;
                }
            }
        }

        for (int i = 0; i < 7; i += 3)
        {
            if (!przycisk[i].getText().equals(""))
            {
                if (przycisk[i].getText().equals(przycisk[i + 1].getText()) && przycisk[i + 1].getText().equals(przycisk[i + 2].getText()))
                {
                    jestWygrany = true;
                    break;
                }
            }
        }

        if (!przycisk[0].getText().equals(""))
        {
            if (przycisk[0].getText().equals(przycisk[4].getText()) && przycisk[0].getText().equals(przycisk[8].getText()))
            {
                jestWygrany = true;
            }
        }
        if (!przycisk[2].getText().equals(""))
        {
            if (przycisk[2].getText().equals(przycisk[4].getText()) && przycisk[4].getText().equals(przycisk[6].getText()))
            {
                jestWygrany = true;
            }
        }
    }

    public void wygranaczyremis()
    {

        if (jestWygrany)
        {
            Toast.makeText(this.aktywnosc, "Wygral: " + arr[Kolko_krzyzyk.ruchy % 2], Toast.LENGTH_LONG).show();
            for (Button buttons: przycisk)
            {
                buttons.setText("");
                buttons.setEnabled(true);
            }
            jestWygrany = false;
        }
        else
        {
            for (Button buttons: przycisk)
            {
                if(buttons.getText().equals(""))
                {
                    return;
                }
            }
            if (!(pustePole))
            {
                Toast.makeText(this.aktywnosc,"Remis!" , Toast.LENGTH_LONG).show();
                for (Button buttons: przycisk)
                {
                    buttons.setText("");
                    buttons.setEnabled(true);
                }
            }
        }
    }
}