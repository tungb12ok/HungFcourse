/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.Feedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tungl
 */
public class FeedbackDAO extends MyDAO {

    public void addFeedback(Feedback feedback) {
        String sql = "INSERT INTO [dbo].[Feedback]\n"
                + "           ([CourseID]\n"
                + "           ,[Comment]\n"
                + "           ,[UserID]\n"
                + "           ,[Like]\n"
                + "           ,[ReactID])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedback.getCourseID());
            ps.setString(2, feedback.getComment());
            ps.setInt(3, feedback.getUserID());
            ps.setInt(4, feedback.getLike());
            ps.setInt(5, feedback.getReactID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Feedback> getFeedbacksByCourseID(int courseID) {
        String sql = "SELECT [FeedbackID], [Comment], [UserID], [Like], [ReactID] FROM [dbo].[Feedback] WHERE [CourseID] = ?";
        List<Feedback> feedbackList = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, courseID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackID(rs.getInt(1));
                feedback.setComment(rs.getString(2));
                feedback.setUserID(rs.getInt(3));
                feedback.setLike(rs.getInt(4));
                feedback.setReactID(rs.getInt(5));
                feedbackList.add(feedback);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return feedbackList;
    }

    public void updateFeedback(Feedback feedback) {
        String sql = "UPDATE [dbo].[Feedback]\n"
                + "SET [Comment] = ?, [Like] = ?, [ReactID] = ?\n"
                + "WHERE [FeedbackID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, feedback.getComment());
            ps.setInt(2, feedback.getLike());
            ps.setInt(3, feedback.getReactID());
            ps.setInt(4, feedback.getFeedbackID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteFeedback(int feedbackID) {
        String sql = "DELETE FROM [dbo].[Feedback] WHERE [FeedbackID] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, feedbackID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        FeedbackDAO fDAO = new FeedbackDAO();
        Feedback f = new Feedback();
        f.setComment("ewqe");
        f.setCourseID(1);
        f.setUserID(1);
        f.setLike(4);
        fDAO.addFeedback(f);
    }
}
