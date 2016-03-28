package qyl.com.opengldemos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qiuyunlong on 16/3/22.
 */
public class Data implements Parcelable
{
    public int id;
    public String name;

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>()
    {
        public Data createFromParcel(Parcel in)
        {
            return new Data(in);
        }

        public Data[] newArray(int size)
        {
            return new Data[size];
        }
    };

    public Data()
    {
    }

    private Data(Parcel in)
    {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in)
    {
        id = in.readInt();
        name = in.readString();
    }

    public int describeContents()
    {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(id);
        dest.writeString(name);
    }
}
