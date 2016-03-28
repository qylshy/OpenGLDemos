// IaidlData.aidl
package qyl.com.opengldemos;

// Declare any non-default types here with import statements
import qyl.com.opengldemos.Data;

interface IaidlData {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    int getPid();

    String getName();

    Data getData();
}
