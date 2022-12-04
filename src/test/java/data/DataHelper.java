package data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    public static String generateNumber() {
        Faker faker = new Faker();
        return faker.finance().creditCard();
    }

    public static String generateLongNumber() {

        Faker faker = new Faker(new Locale("us"), new RandomService());
        return faker.numerify("####################");
    }

    public static String generateShortNumber() {

        Faker faker = new Faker(new Locale("us"), new RandomService());
        return faker.numerify("##########");
    }

    public static String generateMonth() {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Random random = new Random();
        int i = random.nextInt(months.length);
        return months[i];
    }

    public static String generateYear() {
        Random random = new Random();
        int rnd = random.nextInt(5) + 22;
        return Integer.toString(rnd);
    }

    public static String generateInvalidMonth() {
        Random random = new Random();
        int rnd = random.nextInt(87) + 13;
        return Integer.toString(rnd);
    }

    public static String generatePastYear() {
        Random random = new Random();
        int rnd = random.nextInt(23);
        return Integer.toString(rnd);
    }

    public static String generateOwner() {
        Faker faker = new Faker(new Locale("us"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateCyrillicOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateLongOwner() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 130;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

        return generatedString;
    }


    public static String generateOwnerWithHyphenAtStart() {
        Faker faker = new Faker(new Locale("us"));
        return "-" + faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generateOwnerWithHyphenAtEnd() {
        Faker faker = new Faker(new Locale("us"));
        return faker.name().firstName() + "- " + faker.name().lastName() + "-";
    }

    public static String generateCvvCode() {
        Random random = new Random();
        int rnd = random.nextInt(900) + 100;
        return Integer.toString(rnd);
    }

    public static String generateShortCvvCode() {
        Random random = new Random();
        int rnd = random.nextInt(90) + 10;
        return Integer.toString(rnd);
    }

    public static String generateLongCvvCode() {
        Random random = new Random();
        int rnd = random.nextInt(90) + 1000;
        return Integer.toString(rnd);
    }

    public static String generateLatin() {
        Faker faker = new Faker(new Locale("us"), new RandomService());
        return faker.letterify("???");
    }

    public static String generateCyrillic() {
        Faker faker = new Faker(new Locale("ru"), new RandomService());
        return faker.letterify("???");
    }

    public static String getHyphens() {
        return "---";
    }

    public static String getSpecialCharacters() {
        String[] specials = {"!", "@", "#", "$", "%", "^", "&", "*", "ƒ", "©", "˙", "∆", "˚", "¬", "µ", "~", "˜", "√", "ç", "≈"};
        Random random = new Random();
        int i = random.nextInt(specials.length);
        return specials[i];
    }

    public static String generateNumbers() {
        Faker faker = new Faker(new Locale("us"), new RandomService());
        return faker.numerify("###");
    }

    public static class Card {
        private Card() {
        }

        public static CardInfo generateValidInfo() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithLongNumber() {
            CardInfo info = new CardInfo(generateLongNumber(), generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }


        public static CardInfo generateInfoWithShortNumber() {
            CardInfo info = new CardInfo(generateShortNumber(), generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithEmptyNumber() {
            CardInfo info = new CardInfo("", generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithZeroNumber() {
            CardInfo info = new CardInfo("0000000000000000", generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithLatinNumber() {
            CardInfo info = new CardInfo(generateLatin(), generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithCyrillicNumber() {
            CardInfo info = new CardInfo(generateCyrillic(), generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithSpecialsNumber() {
            CardInfo info = new CardInfo(getSpecialCharacters(), generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithApprovedNumber() {
            CardInfo info = new CardInfo("4444444444444441", generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithDeclinedNumber() {
            CardInfo info = new CardInfo("4444444444444442", generateMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithPastDate() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generatePastYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithInvalidDate() {
            CardInfo info = new CardInfo(generateNumber(), generateInvalidMonth(), generateYear(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithZeroDate() {
            CardInfo info = new CardInfo(generateNumber(), "00", "00", generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithEmptyDate() {
            CardInfo info = new CardInfo(generateNumber(), "", "", generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithCyrillicDate() {
            CardInfo info = new CardInfo(generateNumber(), generateCyrillic(), generateCyrillic(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithLatinDate() {
            CardInfo info = new CardInfo(generateNumber(), generateLatin(), generateLatin(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithSpecialsDate() {
            CardInfo info = new CardInfo(generateNumber(), getSpecialCharacters(), getSpecialCharacters(), generateOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithLongOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateLongOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithHyphenAtStartOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwnerWithHyphenAtStart(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithHyphenAtEndOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwnerWithHyphenAtEnd(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoEmptyOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), "", generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoNumericOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateNumber(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoOnlyHyphensOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), getHyphens(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithSpecialsOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), getSpecialCharacters(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoCyrillicOwner() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateCyrillicOwner(), generateCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithShortCvv() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), generateShortCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithLongCvv() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), generateLongCvvCode());
            return info;
        }

        public static CardInfo generateInfoWithZeroCvv() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), "000");
            return info;
        }

        public static CardInfo generateInfoWithEmptyCvv() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), "");
            return info;
        }

        public static CardInfo generateInfoWithCyrillicCvv() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), generateCyrillic());
            return info;
        }

        public static CardInfo generateInfoWithLatinCvv() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), generateLatin());
            return info;
        }

        public static CardInfo generateInfoWithSpecialsCvv() {
            CardInfo info = new CardInfo(generateNumber(), generateMonth(), generateYear(), generateOwner(), getSpecialCharacters());
            return info;
        }
    }

    @Value
    public static class CardInfo {
        private String number;
        private String month;
        private String year;
        private String owner;
        private String cvv;
    }
}
