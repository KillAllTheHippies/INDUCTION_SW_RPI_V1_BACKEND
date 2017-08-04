package model;

import controller.InductionSWController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jamie on 29/02/16.
 */
// TODO: Add Contact number, Email, First aid trained y/n?

public class Inductee implements Serializable {

        private String name, email, jobTitle, carReg, phoneNum;
        private Boolean firstAidTrained;
        private String UUID;
        private long dateOfInduction;
        transient BufferedImage photo;
        private int[] quizAnswers;
        private int index; // index in the arrayList of inductees
        // Collect the wrong answers in the format "Wrong_answer|Correct_answer|Question_index"
        private ArrayList<String> wrongAnswers;
        //TODO: get rid of serialVersionUID
//        private static long serialVersionUID = 1L;

        // transient ArrayList<BufferedImage> documents;

    public Inductee(String name, String phoneNum, String email, String jobTitle,
                    String carReg, Boolean firstAidTrained, long dateOfInduction )
    {

        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.carReg = carReg;
        this.jobTitle = jobTitle;
        this.dateOfInduction = dateOfInduction;
        this.firstAidTrained = firstAidTrained;

        /* Initialise the quiz results array to the size of the questionnaire */
        this.quizAnswers = new int[InductionSWController.getInstance().getQuestionnaire().getQuestions().size()];
        /* Initialise the wrong answers arraylist */
        this.wrongAnswers = new ArrayList<String>();
    }

    /**
     * Record the answer
     * @param answer
     * @param questionIndex
     */
    public void addAnswer(int answer, int questionIndex) {

        this.quizAnswers[questionIndex] = answer;
    }

    public int getQuizPercentCorrect() {
        int quizScore = InductionSWController.getInstance().getQuestionnaire().getQuestions().size() - wrongAnswers.size();
        return ((quizScore * 100) / InductionSWController.getInstance().getQuestionnaire().getQuestions().size());
    }

    /**
     * Retrieve an answer given its index
     * @param index
     * @return
     */
    public int getAnswer(int index) {
        return quizAnswers[index];
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // write the documents
//        out.writeInt(documents.size()); // how many images are serialized?
//        for (BufferedImage eachImage : documents) {
//            ImageIO.write(eachImage, "png", out); // png is lossless
//        }
        // write the photograph if it exists
        if (photo != null) {
            ImageIO.write(photo, "png", out);
        }
//        else {
//            ImageIO.write(new BufferedImage(640,480,photo.getType()), "png", out);
//        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
//        final int imageCount = in.readInt();
//        documents = new ArrayList<BufferedImage>(imageCount);
//        for (int i=0; i<imageCount; i++) {
//            documents.add(ImageIO.read(in));
//        }
        photo = ImageIO.read(in);
    }

    @Override
    public String toString() {
        return "index: " + index + " name: " +name +  "\n Email: " + email +
                "\nJob Title: " + jobTitle + "\nVehicle Registration: " + carReg +
                "\nFirst Aid Trained?: " + firstAidTrained +
                "\nDate of Induction: " + new Date(this.dateOfInduction);
    }

    /* *************<- GETTERS AND SETTERS ->************
    * ***************************************************
    * ***************************************************/

    public Boolean getFirstAidTrained() {
        return firstAidTrained;
    }

    public void setFirstAidTrained(Boolean firstAidTrained) {
        this.firstAidTrained = firstAidTrained;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public ArrayList<String> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(ArrayList<String> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    public int[] getQuizAnswers() {
        return quizAnswers;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarReg() {
        return carReg;
    }

    public void setCarReg(String carReg) {
        this.carReg = carReg;
}

    public long getDateOfInduction() {
        return dateOfInduction;
    }

    public void setDateOfInduction(long dateOfInduction) {
        this.dateOfInduction = dateOfInduction;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BufferedImage getPhoto() {
        // null check

        return photo;
    }

    public void setPhoto(BufferedImage photo) {
        this.photo = photo;
    }

//    public ArrayList<BufferedImage> getDocuments() {
//        return documents;
//    }
//
//    public void setDocuments(ArrayList<BufferedImage> documents) {
//        this.documents = documents;
//    }
}
