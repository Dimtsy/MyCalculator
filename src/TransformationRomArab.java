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

        List<RomNum> romanNum = RomNum.getTransformValues();

        int i = 0;
        String symbolRom = null;

        while (i < romanNum.size()) {
            RomNum symbolListEnum2 = romanNum.get(i);
            if (symbolListEnum2.getValue() == number) {
                symbolRom = symbolListEnum2.name();
            }
            i++;

        }

        return symbolRom;
    }
}
