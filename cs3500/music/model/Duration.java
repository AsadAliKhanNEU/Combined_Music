package cs3500.music.model;

/**
 * The Duration is when the note starts till when the note ends. The length of a note is
 * when it ends negated by when it starts. A note is measured in beats. 4 beats make a measure.
 * The Duration can only be positive integers for now so startAt and endAt cannot be negative.
 * Furthmore the music is progressing so it cannot have an end point before its start point.
 */
public class Duration {
  int startAt;
  int endAt;
  int length; ///beats

  ///the constructor

  Duration(int startAt, int endAt, int length) {
    if (startAt >= 0) {
      this.startAt = startAt;
    }
    if (endAt >= 0 && endAt > startAt) {
      this.endAt = endAt;
    }

    if (length >= endAt - startAt) {
      this.length = length;
    } else {
      // Duration is not an integer
      throw new IllegalArgumentException("Invalid Duration: " + length);
    }
  }

  //get the int endAt field

  public int getendAt() {
    return this.endAt;
  }

  //get the int startAt field

  public int getstartAt() {
    return this.startAt;
  }
  public void setStartAt(int x) {
    this.startAt = x;
  }
  public void setEndAt(int x) {
    this.endAt = x;
  }
}
