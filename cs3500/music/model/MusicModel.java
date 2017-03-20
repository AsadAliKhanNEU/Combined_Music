package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is the model of our music editor. It will manage all the data and execute the operations
 * from the interface. A MusicModel also initializes the game as an empty list of Notes.
 */
public class MusicModel implements MusicOperations<Note> {

  ///To hold our notes
  public final List<Note> Music = new ArrayList<Note>();

  @Override
  ///Note Soundness is cemented in it's class, we do not have to check if it is a valid input here.
  public void addNote(Note x) {
    boolean consists = true;
    for (int i = 0; i < Music.size(); i++) {
      if (Music.get(i) == x) {
        consists = false;
      }
    }
    if (consists) {
      Music.add(x);
    } else {
      throw new IllegalArgumentException("Note already exists!");
    }
  }

  @Override
  public void edit(Note x, Note y) {
    for (int i = 0; i < Music.size(); i++) {
      if (Music.get(i) == x) {
        Music.get(i).pitch = y.pitch;
        Music.get(i).octave = y.octave;
        Music.get(i).dur = y.dur;
      } else {
        throw new IllegalArgumentException("Note:" + x + "does not exist");
      }
    }
  }

  @Override
  public void remove(Note x) {
    if (Music.contains(x)) {
      for (int i = 0; i < Music.size(); i++) {
        if (Music.get(i) == x) {
          Music.remove(i);
          break;
        }
      }
    } else {
      throw new IllegalArgumentException("Note:" + x + "does not exist");
    }

  }

  @Override
  public void combine(List<Note> x, String y) {
    int last;
    if (Music.isEmpty()) {
      last = 0;
    } else {
      last = Music.get(Music.size() - 1).getDur().getendAt();
    }


    switch(y) {
      case "with": Music.addAll(x);
      break;
      case "after":
        incEach(x, last);
        Music.addAll(x);
        break;
      default: throw new IllegalArgumentException("Invalid Command: " + y);
    }
  }
  ///increment each element by x
  public void incEach(List<Note> x, int last) {
    for (int i = 0; i < x.size(); i++) {
      Duration d = x.get(i).getDur();
      d.setStartAt(last + d.getstartAt());
      d.setEndAt(last + d.getendAt());
    }
  }

  /**
   * Helper method to configure the number of columns.
   * If you're on the last octave then print the last possible pitch
   * otherwise print all possible pitches till the next octave.
   *
   * @param i   the octave to be printed
   * @param max the max value a note can be according to its pitch
   * @return number of columns
   */
  protected int columns(int i, int max) {
    if (i == max) {
      return max;
    } else {
      return 11;
    }
  }

  @Override
  public String getMusicState() {

    String musicState = "";
    if (Music.isEmpty()) {
      return musicState;
    }
    ///Find end point of song(last note endAt beat)
    ///to set the required space for beats column
    ///We need this now to know where to start our first line
    List<Integer> beats = new ArrayList<>();
    for (int i = 0; i < Music.size(); i++) {
      beats.add(Music.get(i).getDur().endAt);
    }
    ///Add necessary gaps to first line
    int beatMax = Collections.max(beats);
    int bmLength = String.valueOf(beatMax).length();
    for (int i = 0; i < bmLength - 2; i++) {
      musicState += " ";
    }
    ///Accumulate pitches and octaves to empty lists to determine min and max values
    List<Integer> firstline1 = new ArrayList<>();
    List<Integer> firstline2 = new ArrayList<>();
    List<Note> firstline = new ArrayList<>();
    for (int i = 0; i < Music.size(); i++) {
      firstline1.add(Music.get(i).pitch);
    }
    for (int i = 0; i < Music.size(); i++) {
      firstline2.add(Music.get(i).octave);
    }
    int pitchMin = Collections.min(firstline1);
    int pitchMax = Collections.max(firstline1);
    int octaveMin = Collections.min(firstline2);
    int octaveMax = Collections.max(firstline2);

    ///make all possible combination between min and max values and add
    ///to an empty list
    for (int i = octaveMin; i <= octaveMax; i++) {
      for (int j = pitchMin; j <= columns(i, pitchMax); j++) {
        firstline.add(new Note(j, i, null));
      }
    }

    ///loop the list with the correct character spaces between them(5)
    for (int k = 0; k < firstline.size(); k++) {
      switch (firstline.get(k).toString().length()) {
        case 2:
          musicState += "  " + firstline.get(k).toString() + " ";
          break;
        case 3:
          musicState += " " + firstline.get(k).toString() + " ";
          break;
        case 4:
          musicState += " " + firstline.get(k).toString() + "";
          break;
        default:
          musicState += "" + firstline.get(k).toString() + "";
      }
    }
    musicState += "\n";

    ///Now each corresponding line:
    ///Print each line vertically
    for (int i = 0; i < beatMax; i++) {
      musicState += i;
      ///horizontally
      ///loop through 5 character spaces
      for (int j = 0; j < firstline.size(); j++) {
        ///loop through list for possible X or | or empty
        for (int k = 0; k < Music.size(); k++) {
          if (Music.get(k).getDur().startAt == i
                  && Music.get(k).pitch == firstline.get(j).pitch
                  && Music.get(k).octave == firstline.get(j).octave) {
            musicState += "  X  ";
          } else {
            if (Music.get(k).getDur().endAt == i
                    && Music.get(k).pitch == firstline.get(j).pitch
                    && Music.get(k).octave == firstline.get(j).octave) {
              musicState += "  |  ";
            } else {
              if (Music.get(k).getDur().startAt < i
                      && Music.get(k).getDur().endAt >= i
                      && Music.get(k).pitch == firstline.get(j).pitch
                      && Music.get(k).octave == firstline.get(j).octave) {
                musicState += "  |  ";
              }
            }
          }
        }
        if (!(Music.contains(firstline.get(j)))) {
          musicState += "     ";
        }
      }
      musicState += "\n";
    }
    ///required new line at the end
    return musicState += "\n";
  }
}
