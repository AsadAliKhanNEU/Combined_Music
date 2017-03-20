package cs3500.music.model;

/**
 * A Note is a tone(pitch-octave) and a duration(beats). There are twelve pitches(Western system of
 * music) and ten octaves. When no notes are playing we say the music is at rest which is also
 * measured in beats. Tone will not have its seperate class and will rather be implemented here
 * to avoid unnecessary complexity for now.
 * NOTES: Volume is considered constant for now.
 */
public class Note {

  ///A pitch can only be one of the following:
  public String[] pitches = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
  ///An octave can only be one of the following:
  public String[] octaves = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

  int pitch;
  int octave;
  Duration dur;

  ///the constructor
  Note(int pitch, int octave, Duration dur) {
    if (pitch >= 0 && pitch <= 11) {
      this.pitch = pitch;
    } else {
      throw new IllegalArgumentException("Invalid Pitch: " + pitch);
    }
    if (octave >= 0 && octave <= 9) {
      this.octave = octave;
    } else {
      throw new IllegalArgumentException("Invalid Octave: " + pitch);
    }
    this.dur = dur;
  }

  @Override
  public String toString() {
    return "" + pitches[this.pitch] + octaves[this.octave];
  }

  //get the Duration dur field
  public Duration getDur() {
    return this.dur;
  }
}
