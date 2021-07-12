public class TPC07 {
    public static void main(String[] args) {

        int a = 20;
        float b = 56.7f;
        // a+b=?
        float v = sum(a,b); // call by value(값)
        System.out.println(v); //76.7

        int[] arr = {1,2,3,4,5};
        // 배열의 총합 = ?
        int vv = arrSum(arr); //call by reference(번지)
        System.out.println(vv);
    }
    // 하나의 클래스 안에서는 private 상관없이 호출 가능
    private static int arrSum(int[] arr)
    {
        int hap = 0;
        for(int i = 0; i< arr.length;i++)
        {
            hap+= arr[i];
        }
        return hap;
    }
    public static float sum(int a, float b)
    {
        float v = a + b;
        return v;
    }


}
