public class Bucket {
    public int vol;
    public int curvol;
    // vol - volume, объём ведра
    // curvol - current volume, текущий объём воды в ведре

    public Bucket (int vol)
    {
        this.vol = vol;
        this.curvol = 0;
    }
    public void fillBucket()
    {
        //заполнить ведро до краёв
        this.curvol = this.vol;
    }
    public void pourInAB(Bucket buc)
    {
        //перелить из одного ведра во второе
        buc.curvol += this.curvol;
        this.curvol = 0;
        if(buc.curvol > buc.vol){
            this.curvol = buc.curvol - buc.vol;
            buc.curvol = buc.vol;
        }
    }
    public void pourOut()
    {
        //опорожнить ведро
        this.curvol = 0;
    }

}
