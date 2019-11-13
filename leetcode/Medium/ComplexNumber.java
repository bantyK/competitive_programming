package solutions.medium;

// 537 : https://leetcode.com/problems/complex-number-multiplication/
public class ComplexNumber {
    public static void main(String[] args) {
        ComplexNumber number = new ComplexNumber();
        String res = number.complexNumberMultiply("78+-76i", "-86+72i");
        System.out.println(res);
    }

    public String complexNumberMultiply(String a, String b) {
        String[] number1 = a.split("\\+");
        String[] number2 = b.split("\\+");

        String num1realS = number1[0];
        int num1real = Integer.parseInt(num1realS);

        String num2realS = number2[0];
        int num2real = Integer.parseInt(num2realS);

        String num1ImgS = number1[1];
        int num1Img = Integer.parseInt(num1ImgS.substring(0, num1ImgS.length() - 1) + "");

        String num2ImgS = number2[1];
        int num2Img = Integer.parseInt(num2ImgS.substring(0, num2ImgS.length() - 1));


        int resReal = (num1real * num2real) - (num1Img * num2Img);

        int resImg = (num1real * num2Img) + (num2real * num1Img);

        return resReal + "+" + resImg + "i";
    }
}
