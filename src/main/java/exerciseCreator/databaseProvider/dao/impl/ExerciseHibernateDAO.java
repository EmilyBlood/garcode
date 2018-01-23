package exerciseCreator.databaseProvider.dao.impl;

import exerciseCreator.databaseProvider.dao.IExerciseDAO;
import exerciseCreator.databaseProvider.entity.Exercise;
import exerciseCreator.databaseProvider.entity.TestCase;
import exerciseCreator.databaseProvider.session.HibernateSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExerciseHibernateDAO implements IExerciseDAO{

    @Override
    public List<Exercise> findAll() {
        try (Session session = HibernateSession.getSession()) {
            TypedQuery<Exercise> query = session.createQuery("from Exercise", Exercise.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public Exercise findById(Integer exerciseId) {
        try (Session session = HibernateSession.getSession()) {
            TypedQuery<Exercise> query = session.createQuery("from Exercise where id=:exerciseId", Exercise.class);
            query.setParameter("exerciseId", exerciseId);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addExercise(Exercise exercise) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.save(exercise);
            tx.commit();
            String path = exercise.pathToExercises + exercise.getId().toString();
            //System.out.println(path);

            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                    file.setExecutable(true, false);
                    file.setReadable(true, false);
                    file.setWritable(true, false);
                } else {
                    System.out.println("Failed to create directory!");
                }
            }
            tx = session.beginTransaction();
            exercise.setPathToExercises(path);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteExercise(Exercise exercise) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            session.delete(exercise);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void addTestCaseForExercise(Exercise exercise, TestCase testCase) {
        Transaction tx = null;
        try (Session session = HibernateSession.getSession()) {
            tx = session.beginTransaction();
            exercise.addTestCase(testCase);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}