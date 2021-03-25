package pollub.ism.lab04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    Kolko_krzyzyk Kolko_krzyzyk;

    public void kliknieciePrzycisku(View view) throws InterruptedException
    {
        int czyjRuch = Kolko_krzyzyk.ruchy % 2;
        Kolko_krzyzyk.rysowanie(view, czyjRuch);
        Kolko_krzyzyk.czyWygrana();
        Kolko_krzyzyk.wygranaczyremis();
        Kolko_krzyzyk.ruchy++;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Kolko_krzyzyk = new Kolko_krzyzyk(this);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        char[] getSigns = (char[]) savedInstanceState.getSerializable("boardSigns");
        for (int i = 1; i <= 9; i++)
        {
            int id = getResources().getIdentifier("button" + i, "id", getPackageName());
            Kolko_krzyzyk.przycisk[i - 1] = (Button) findViewById(id);
            if ((String.valueOf(getSigns[i - 1]).equals("X") || String.valueOf(getSigns[i - 1]).equals("O")))
            {
                Kolko_krzyzyk.przycisk[i - 1].setEnabled(false);
                Kolko_krzyzyk.przycisk[i - 1].setText(String.valueOf(getSigns[i - 1]));
            }
            else
            {
                Kolko_krzyzyk.przycisk[i - 1].setText("");
            }
        }
        Kolko_krzyzyk.ruchy = savedInstanceState.getInt("numberOfMoves");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("boardSigns", Kolko_krzyzyk.sprawdzPrzycisk());
        outState.putInt("numberOfMoves", Kolko_krzyzyk.ruchy);
    }
}