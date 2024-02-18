package GUI;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 * Klasa używana do ograniczenia pola tekstowego
 */
public class MaxLengthTextField extends JTextField {
    /**
     * Maksymalna długość pola tekstowego
     */
    private int maxLength;

    /**
     * Konstruktor ustawienia maksymalnej długości pola tekstowego
     * @param maxLength - maksymalna długość
     */
    public MaxLengthTextField(int maxLength) {
        this.maxLength = maxLength;
        ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentLengthFilter(maxLength));
    }

    /**
     * Klasa filtrująca pole tekstowe
     */
    private class DocumentLengthFilter extends DocumentFilter {
        /**
         * Maksymalna długość pola tekstowego
         */
        private int maxLength;

        /**
         * Konstruktor filtrowania pola tekstowego
         * @param maxLength - maksymalna długość
         */
        public DocumentLengthFilter(int maxLength) {
            this.maxLength = maxLength;
        }

        /**
         * Przesłonięcie metody insertString. Sprawdzane jest czy długość tekstu przekracza maksymalną wartość
         * @param fb FilterBypass that can be used to mutate Document
         * @param offset  the offset into the document to insert the content &gt;= 0.
         *    All positions that track change at or after the given location
         *    will move.
         * @param string the string to insert
         * @param attr      the attributes to associate with the inserted
         *   content.  This may be null if there are no attributes.
         * @throws BadLocationException
         */
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (fb.getDocument().getLength() + string.length() > maxLength) {
                return;
            }
            super.insertString(fb, offset, string, attr);
        }

        /**
         * Przesłonięcie metody replace. Nadlimitowy tekst jest usuwany
         * @param fb FilterBypass that can be used to mutate Document
         * @param offset Location in Document
         * @param length Length of text to delete
         * @param text Text to insert, null indicates no text to insert
         * @param attrs AttributeSet indicating attributes of inserted text,
         *              null is legal.
         * @throws BadLocationException
         */
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - maxLength;

            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }

            if (length > 0 && text.length() < length) {
                fb.remove(offset, length);
            }

            if (text.length() > 0) {
                super.insertString(fb, offset, text, attrs); // Wstaw nowy tekst
            }
        }
    }
}