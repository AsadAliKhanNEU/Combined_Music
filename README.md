# Combined_Music

Asad Ali Khan
Music Editor:- 

First we will deal with the data for our Model:

Music is playing a collection of tones for varying durations on various instruments at 
various volumes at varying times. We will only be dealing withtones, durations and times for this assignment. 

Class Note:-
A Note is a tone(pitch, octave) and a duration(beats, times). There are twelve 
pitches(Western system of music) and ten octaves. When no notes are playing we say the 
music is at rest which is also measured in beats. I did not create a separate class for 
tone inside Note to avoid unnecessary complexity. A Note has three fields, int pitch, int
octaves and Duration duration. Fields and pitches are keys to extract from an array of 
Strings. 
Limitations of Note-
int pitch must be within its designated array size
int octave must be within its designated array size


Class Duration:- Typical pieces have a rhythm to them called a measure, that is four beats
long. The duration is measured in beats and can in general be any integer multiple of any 
integral power of two beats long. The Duration class will have three fields, int startAt,
int endAt, int length. startAt indicates when the Note begins, endAt indicates when it ends
and length indicates the actual duration of that note. 
Limitations of Duration-
int startAt must be positive
int endAt must be positive and greater than startAt as the music can only progress forward.
int length must be equal to endAt - startAt.

Desired Operations:
Create music one note at a time
Edit or remove existing notes
Combine two pieces such that they play simultaneously or consecutively
Manage how music is played and visualize the music being played

Interface MusicOperations:-
Will hold these desired operations for future implementation

addNote(Note x)
function: add a note to the current music
limitations: if note already exists then throw an IllegalArgumentException 

edit(Note x, Note y)
function: Turn all instances of note x's fields to note y's fields. With this you can
either increase, decrease any or all fields in Note
limitations: If an instance of Note x can't be found then an IllegalArgumentException
will be thrown

remove(Note x)
function: Remove any instance of Note x from the music. 
limitations: If an instance of Note x can't be found then an IllegalArgumentException
will be thrown

combine(List<Note> x, String y)
function: Add List<Note> x to the music either with it or after it. If y is "with" then
combine will add all the elements of x to the music as they are, however if y is "after" 
then it will play the added music only after the current music is done playing. This addition
is done by adding the last endsAt of the current music to the fields startsAt and endAt of 
every element of List<Note>.
limitations: If the String input y is not "with" or "after" then an IllegalArgumentException
will be thrown

getMusicState()
function: Returns the present state of the music editor as a string. The String is foramtted
as follows:

[b][b] [b]C1[b][b],.....,[b]D#2[b],......,[b]G3[b][b]...[n]Min and Max pitch and octaves
0[b]  [b][b]X[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] X to indicate start of Note
5[b]  [b][b]|[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] | to indicate continuation of Note
9[b]  [b][b]|[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] last | to indicate end of Note
10  [b][b]X[b][b],..,[b][b][b][b][b]  [b][b][b][b][b][n] X to indicate another start
    
where [b] is a single blankspace, [n] is newline. Note that there is a
newline on the last line. Also note that the space in the digit column on the left
is relative to the number of digits in it. If the music plays till 999 beats then 3 spaces
must be provided and if greater than 999 then 4 spaces must be provided and so on. Each note
is allocated exactly 5 spaces for both its heading in the first row and input. The spaces
between the blanks do not indicate characters in a string but are merely put there for
readability.

Class MusicModel:-
This class is the model of Editor. It implements the interface and stacks the Notes
in a list called List<Note> Music.

The only added method is a helper method called  
column(int i, int max)
function: If you're on the last octave then print the last possible pitch otherwise print 
all possible pitches till the next octave. This helper ensures that the for-loop to create
the first row of Notes starts at the minimum Note and prints all Notes till the maximum Note.

