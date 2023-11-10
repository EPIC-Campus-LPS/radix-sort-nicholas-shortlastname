import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class radixSortTest {

    @Test
    @DisplayName("Sort List")
    void sort() {
        radixSort sorter = new radixSort();
        sorter.sort("testing.txt");

        String words = "";
        try {
            Scanner file = new Scanner(new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\radix-sort-nicholas-shortlastname\\TextFiles\\file_name_key.txt"));
            while (file.hasNext()) {
                words += file.nextLine() + " ";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String finalWords = words;
        assertAll(() -> assertEquals("ComMANDEERs  DiSeMbOdyING  enthusiasm  EXPLORATIONS  LIBELEES  milder  pAnDErING  ReFurbISHMENT  spectroscopy  STENCILED ",
                        finalWords),
                () -> assertThrows(RuntimeException.class, () -> sorter.sort("doesnt_exist.txt")));
    }
}