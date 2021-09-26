import java.util.Arrays;
import java.util.List;

public class TransformationRomArab {
    enum RomNum {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7),
        VIII(8), IX(9), X(10), XI(11), XII(12), XIII(13), XIV(14),
        XV(15), XVI(16), XVII(17), XVIII(18), XIX(19), XX(20);

        private int value;

        RomNum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomNum> getTransformValues() {
            return Arrays.asList(RomNum.values());
        }
    }

enum ArabRomNum {
        C(100), XC(90), L(50), XL(40), X(10), IX(9), V(5), IV(4), I(1);

        private int value;

        ArabRomNum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<ArabRomNum> getTransformValues() {
            return Arrays.asList(ArabRomNum.values());
        }
    }

    public static int romToArab(String input) throws MyExeption {
        int result = 0;

        List<RomNum> romNumerals = RomNum.getTransformValues();

        int i = 0;

        while ((input.length() > 0) && (i < romNumerals.size())) {
            RomNum symbolListEnum = romNumerals.get(i);
            if (input.equals(symbolListEnum.name())) {
                result = symbolListEnum.getValue();
            }
            i++;
        }

        if (result == 0 | result > 10) {
            throw new MyExeption("Число не подходит, введите римское число от I до X вместо : " + input);
        }

        return result;
    }

    public static String arabToRom(int number) throws MyExeption {
        if (number <= 0) {
            throw new MyExeption(number + "Римское число не может быть <=0");
        }

        List<ArabRomNum> romanNum = ArabRomNum.getTransformValues();

        int i = 0;
        StringBuilder stringB = new StringBuilder(); 

        while (i < romanNum.size()) {
        ArabRomNum curSymbol = romanNum.get(i);
        if (curSymbol.getValue() <= number) { 
        stringB.append(curSymbol.name());
        number -= curSymbol.getValue();
        } 
        else { 
              i++; 
         }
        } 
            return stringB.toString();
    }
}
