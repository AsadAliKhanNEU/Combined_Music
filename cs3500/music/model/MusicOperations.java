package cs3500.music.model;

import java.util.List;

/**
 * This is the interface of the Music model. It is parameterized over the
 * Note type, the benefit of this interface parameterization is to allow different model
 * implementations in the future if need be.
 */
public interface MusicOperations<Note> {

  /**
   * Add a note to the Music. If there is already a note at the location then don't add it
   * as it already exists in that location. Notes can only be added within 10 octave
   * levels of pitches C C# D D# E F F# G G# A A# B
   *
   * @param x note to be added
   * @throws IllegalArgumentException if the Note addition is invalid
   */
  void addNote(Note x) throws IllegalArgumentException;

  /**
   * A note can be edited the following ways:
   * You can change any of the properties
   * Pitch - increase or decrease the pitch of the note
   * Octave - change the octave of the note
   * Duration - change the placement and increase or decrease the length of the note
   *<p>
   * A note must be changed by providing a note with the same properties that are not to be changed
   * and different properties for those that are to be changed basically just changed the note to be
   * edited with your desired note version. Edit will change all instances of note x with note y.
   *</p>
   * @param x The note to be edited
   * @param y The edited version of the note
   * @throws IllegalArgumentException if the Note to be edit does not exist
   */
  void edit(Note x, Note y) throws IllegalArgumentException;

  /**
   * Remove will remove an instance of the note from the music.
   *
   * @param x The note to be removed
   * @throws IllegalArgumentException if the Note to be edit does not exist
   */
  void remove(Note x) throws IllegalArgumentException;

  /**
   * Combine two different pieces of music by either playing them together or one after the other.
   * Input of "with" for y will arrange them together.
   * Input of "after" for y will place x after the current piece.
   *
   * @param x The note to be combined to the existing piece of music
   * @param y A string to specify if x is to be played with or after the existing music
   * @throws IllegalArgumentException if the Note to be edit does not exist
   */
  void combine(List<Note> x, String y) throws IllegalArgumentException;

  /**
   * Returns the present state of the music editor as a string. The String is foramtted
   * as follows:
   * <pre>
   * [b][b] [b]C1[b][b],.....,[b]D#2[b],......,[b]G3[b][b]...[n]Min and Max pitch and octaves
   * 0[b]  [b][b]X[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] X to indicate start of Note
   * 5[b]  [b][b]|[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] | to indicate continuation of Note
   * 9[b]  [b][b]|[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] last | to indicate end of Note
   * 10    [b][b]X[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] X to indicate another start
   *
   * where [b] is a single blankspace, [n] is newline. Note that there is a
   * newline on the last line. Also note that the space in the digit column on the left
   * is relative to the number of digits in it. If the music plays till 999 beats then 3 spaces
   * must be provided and if greater than 999 then 4 spaces must be provided and so on. Each note
   * is allocated exactly 5 spaces for both its heading in the first row and input. The spaces
   * between the blanks do not indicate characters in a string but are merely put there for
   * readability.
   * </pre>
   *
   * @return the formatted string as above
   */
  String getMusicState();
}
