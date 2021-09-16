package system.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioHelper {

    /**
     * Loops an audio file (in .wav, .mid, or .au format) in a background thread.
     *
     * @param pathToFileFromResources the path to the audio file, relative to the resources folder
     * @throws IllegalArgumentException if {@code pathToFileFromResources} is {@code null}
     */
    public static synchronized void loop(String pathToFileFromResources) {
        if (pathToFileFromResources == null) throw new IllegalArgumentException();
        try {
            Clip clip = AudioSystem.getClip();
            InputStream inputStream = AudioHelper.class.getResourceAsStream(pathToFileFromResources);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (UnsupportedAudioFileException e) {
            throw new IllegalArgumentException("unsupported audio format: '" + pathToFileFromResources + "'", e);
        }
        catch (LineUnavailableException | IOException e) {
            throw new IllegalArgumentException("could not play '" + pathToFileFromResources + "'", e);
        }
    }

}
