package back.security;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashingAlgorithms {

    public static String stringToSHA256(String input) {

        return Hashing.sha256()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();
    }

}
