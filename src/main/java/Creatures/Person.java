package Creatures;

import java.awt.*;
import GUI.GUI;
import Structures.SimulationOptions;

import java.util.ArrayList;

/**
 * Klasa osoby, która zostanie zakażona
 */
public class Person
{
    /**
     * Współrzędne pozycji, w której znajduje się osoba
     */
    int x,y;
    /**
     * Przesunięcie, z jakim będzie się poruszać
     */
    int vx,vy;
    /**
     * Którą chorobę aktualnie ma osoba
     */
    int status=0;
    /**
     * Liczba pseudolosowa, wybierająca danej osobie któregoś wirusa
     */
    int randomize;
    /**
     * Liczba zakażonych pierwszym wirusem
     */
    public static int numInfected_0 = 0;
    /**
     * Liczba zakażonych drugim wirusem
     */
    public static int numInfected_1 = 0;
    /**
     * Liczba zakażonych trzecim wirusem
     */
    public static int numInfected_2 = 0;
    /**
     * Liczba zakażonych czwartym wirusem
     */
    public static int numInfected_3 = 0;
    /**
     * Zsumowane parametry pierwszego wirusa
     */
    double attack_0 = 100;
    /**
     * Zsumowane parametry drugiego wirusa
     */
    double attack_1 = 100;
    /**
     * Zsumowane parametry trzeciego wirusa
     */
    double attack_2 = 100;
    /**
     * Zsumowane parametry czwartego wirusa
     */
    double attack_3 = 100;
    /**
     * Parametry przekazane z ekranu GUI
     */
    SimulationOptions params = GUI.simOptions_copy;

    /**
     * Konstruktor osoby
     */
    public Person()
    {
        x = (int)(Math.random()*700+0);
        y = (int)(Math.random()*700+0);

        randomize = (int)(Math.random()*4+1);

        switch(randomize)
        {
            case 1:
                status = 0;
                numInfected_0+=1;
                break;
            case 2:
                status = 1;
                numInfected_1+=1;
                break;
            case 3:
                status = 2;
                numInfected_2+=1;
                break;
            case 4:
                status = 3;
                numInfected_3+=1;
                break;
        }

        vx = (int)(Math.random()*(10+1)+-5);
        vy = (int)(Math.random()*(10+1)+-5);

    }

    /**
     * Metoda symulująca zderzenie się dwóch osób zakażonych różnymi chorobami
     * @param p2 - druga osoba zakażona innym wirusem
     */
    public void fight(Person p2)
    {
        Rectangle vir1 = new Rectangle(p2.x,p2.y,10,10);
        Rectangle vir2 = new Rectangle(this.x,this.y,10,10);
        getStats();

        if (vir1.intersects(vir2))
        {
            //0 z 1
            if (this.status==0 && p2.status==1)
            {
                if(attack_0>attack_1)
                {
                    p2.status=0;
                    numInfected_0+=1;
                    numInfected_1-=1;
                }
                else
                {
                    this.status=1;
                    numInfected_1+=1;
                    numInfected_0-=1;
                }
            }

            else if (this.status==1 && p2.status==0)
            {
                if(attack_0>attack_1)
                {
                    this.status=0;
                    numInfected_0+=1;
                    numInfected_1-=1;
                }
                else
                {
                    p2.status=1;
                    numInfected_1+=1;
                    numInfected_0-=1;
                }
            }

            //0 z 2
            else if (this.status==0 && p2.status==2)
            {
                if(attack_0>attack_2)
                {
                    p2.status=0;
                    numInfected_0+=1;
                    numInfected_2-=1;
                }
                else
                {
                    this.status=2;
                    numInfected_2+=1;
                    numInfected_0-=1;
                }
            }

            else if (this.status==2 && p2.status==0)
            {
                if(attack_0>attack_2)
                {
                    this.status=0;
                    numInfected_0+=1;
                    numInfected_2-=1;
                }
                else
                {
                    p2.status=2;
                    numInfected_2+=1;
                    numInfected_0-=1;
                }
            }

            //0 z 3
            else if (this.status==0 && p2.status==3)
            {
                if(attack_0>attack_3)
                {
                    p2.status=0;
                    numInfected_0+=1;
                    numInfected_3-=1;
                }
                else
                {
                    this.status=3;
                    numInfected_3+=1;
                    numInfected_0-=1;
                }
            }

            else if (this.status==3 && p2.status==0)
            {
                if(attack_0>attack_3)
                {
                    this.status=0;
                    numInfected_0+=1;
                    numInfected_3-=1;
                }
                else
                {
                    p2.status=3;
                    numInfected_3+=1;
                    numInfected_0-=1;
                }
            }

            //1 z 2
            else if (this.status==1 && p2.status==2)
            {
                if(attack_1>attack_2)
                {
                    p2.status=1;
                    numInfected_1+=1;
                    numInfected_2-=1;
                }
                else
                {
                    this.status=2;
                    numInfected_2+=1;
                    numInfected_1-=1;
                }
            }

            else if (this.status==2 && p2.status==1)
            {
                if(attack_1>attack_2)
                {
                    this.status=1;
                    numInfected_1+=1;
                    numInfected_2-=1;
                }
                else
                {
                    p2.status=2;
                    numInfected_2+=1;
                    numInfected_1-=1;
                }
            }

            //1 z 3
            else if (this.status==1 && p2.status==3)
            {
                if(attack_1>attack_3)
                {
                    p2.status=1;
                    numInfected_1+=1;
                    numInfected_3-=1;
                }
                else
                {
                    this.status=3;
                    numInfected_3+=1;
                    numInfected_1-=1;
                }
            }

            else if (this.status==3 && p2.status==1)
            {
                if(attack_1>attack_3)
                {
                    this.status=1;
                    numInfected_1+=1;
                    numInfected_3-=1;
                }
                else
                {
                    p2.status=3;
                    numInfected_3+=1;
                    numInfected_1-=1;
                }
            }

            //2 z 3
            else if (this.status==2 && p2.status==3)
            {
                if(attack_2>attack_3)
                {
                    p2.status=2;
                    numInfected_2+=1;
                    numInfected_3-=1;
                }
                else
                {
                    this.status=3;
                    numInfected_3+=1;
                    numInfected_2-=1;
                }
            }

            else if (this.status==3 && p2.status==2)
            {
                if(attack_2>attack_3)
                {
                    this.status=2;
                    numInfected_2+=1;
                    numInfected_3-=1;
                }
                else
                {
                    p2.status=3;
                    numInfected_3+=1;
                    numInfected_2-=1;
                }
            }
        }
    }

    /**
     * Metoda kolorująca osobę na kolor danego wirusa
     * @param g - dana osoba
     */
    public void paint(Graphics g)
    {
         switch(status)
         {
             case 0:
                 g.setColor(Color.RED);
                 break;
             case 1:
                 Color color = new Color(255, 244, 64);
                 g.setColor(color);
                 break;
             case 2:
                 g.setColor(Color.MAGENTA);
                 break;
             case 3:
                 g.setColor(Color.BLACK);
                 break;
         }

         x+=vx;
         y+=vy;

         if(x < 0 || x >= 690)
         {
            vx *= -1;
         }

         if(y < 0 || y >= 690)
         {
            vy *= -1;
         }
         g.fillOval(x, y, 10, 10);
    }

    /**
     * Parametry chorób, które potem zostają przypisane osobom
     */
    void getStats()
    {
        attack_0 = attack_0*params.virus_Params.get(0);
        attack_1 = attack_1*params.virus_Params.get(1);
        attack_2 = attack_2*params.virus_Params.get(2);
        attack_3 = attack_3*params.virus_Params.get(3);
    }
}
