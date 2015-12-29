// word_addition_java.java
// Rick Elwood - 12-21-15
// Java-based word addition that attempts to improve upon the speed of the python implementation.
// Initial benchmarks show this version is 2940% faster than the python version

import java.io.*;	//Input-output classes.
import java.util.*;

class Main {
    // Adds two words and returns the result
    private static String add_words(String word_1, String word_2){
        int word_length, letter_sum, carry=0;
        String result = "";
        String temp;

        // Pad a word if needed
        if (word_1.length() > word_2.length()){
            word_length = word_1.length();
            temp = "%" + word_length + "s";
            word_2 = String.format(temp, word_2).replace(' ', '`');
        }
        else if (word_2.length() > word_1.length()) {
            word_length = word_2.length();
            temp = "%" + word_length + "s";
            word_1 = String.format(temp, word_1).replace(' ', '`');
        }
        else{
            word_length = word_2.length();
        }

        // Addition loop
        for (int i = word_length-1; i >=0; i--){
            letter_sum = ((int)word_1.charAt(i) + (int)word_2.charAt(i) + carry) - 96;
            if (letter_sum > (int)'z'){
                letter_sum -= 27;
                carry = 1;
            }
            else{
                carry = 0;
            }
            if (letter_sum == 96){
                letter_sum = 32;
            }
            result = (char)letter_sum + result;
        }
        if (carry == 1){
            result = 'a' + result;
        }
        return result;
    }
    public static void main(String[] args)throws IOException {
        BufferedReader inf = null;
        String add_sum, result_string;
        int word_count = 0;

        // Open input file and read words to a list
        try {
            inf = new BufferedReader(new FileReader("wordlist.txt"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        String str;
        List<String> list = new ArrayList<>();
        try{
            while((str = inf != null ? inf.readLine() : null) != null){
                list.add(str);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        // Hash the words for speed
        Set<String> set = new HashSet<>(list);

        // Open file output
        File file = new File("word_sum_results.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        // start timer and start main loop that iterates through the entire dictionary.
        final long startTime = System.currentTimeMillis();
        for (int k =0; k < list.size(); k++){
            for (int j =k; j < list.size();j++){
                add_sum = add_words(list.get(k), list.get(j));
                if (set.contains(add_sum)){
                    word_count ++;
                    result_string = word_count + ": " + list.get(k) + " + " + list.get(j) + " = " + add_sum + "\n";
                    bw.write(result_string);
                    System.out.println(result_string);
                }
            }
        }
        // Output elapsed time and close files.
        final long endTime = System.currentTimeMillis();
        final long elapsed = endTime-startTime;
        System.out.print("Execution time is " + (elapsed/1000d) + " seconds");
        assert inf != null;
        inf.close();
        bw.close();
    }
}
