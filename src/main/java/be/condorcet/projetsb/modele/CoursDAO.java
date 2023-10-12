/*package be.condorcet.projetsb.modele;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.*;

@Component
public class CoursDAO {

    String url ;
    String userid;

    String password;

    Connection dbConnect=null;


    public CoursDAO(@Value("${spring.datasource.url}")String url, @Value("${spring.datasource.username}") String userid, @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.userid = userid;
        this.password = password;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbConnect = DriverManager.getConnection(url, userid, password);
            // connexion avec le login et le password
            // et récupération d'un objet connection
        }
        catch (Exception e){
            System.out.println("erreur : "+e);
            System.exit(0);
        }
    }

    public List<Cours> readall() throws Exception{
        List<Cours> lc = new ArrayList<>();

        try(PreparedStatement pstm = dbConnect.prepareStatement("select * from APICOURS")) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idclient= rs.getInt("ID_COURS");
                String mat = rs.getString("MATIÈRE");
                int heures = rs.getInt("HEURES");
                lc.add(new Cours(idclient,mat,heures));
            }
            if(lc.isEmpty())throw new Exception("aucun Cours");
            return lc;

        } catch (Exception e) {
            throw new Exception("Erreur de lecture " + e.getMessage());
        }
    }

    public Cours read(int id) throws Exception
    {
        String req = "select * from APICOURS where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(req))
        {
            pstm.setInt(1,id);
            try(ResultSet rs = pstm.executeQuery())
            {
                if(rs.next())
                {
                    String mat = rs.getString("MATIÈRE");
                    int heures = rs.getInt("HEURES");
                    Cours cours = new Cours(id,mat,heures);
                    return cours;
                }
                else
                {
                    throw new Exception("Aucun cours trouvé");
                }
            }catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public Cours add(Cours cours)
    {
        String query1 = "insert into APICOURS(MATIÈRE,HEURES) values (?,?)";
        String query2 = "select id_cours from APICOURS where MATIÈRE=?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        )
        {
            pstm1.setString(1,cours.getMatiere());
            pstm1.setInt(2,cours.getHeures());
            int n = pstm1.executeUpdate();
            if(n==1)
            {
                pstm2.setString(1,cours.getMatiere());
                ResultSet rs = pstm2.executeQuery();
                if (rs.next())
                {
                    int id_Cours = rs.getInt(1);
                    cours.setIdcours(id_Cours);
                    return cours;
                }
                else
                {
                    System.out.println("record introuvable");
                    return null;
                }
            }
            else return null;
        }
        catch (SQLException e)
        {
            System.out.println("erreur sql :"+e);
            return null;
        }
    }

    public boolean remove(Cours cours)
    {
        String query = "delete from APICOURS where id_cours = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query))
        {
            pstm.setInt(1,cours.getIdCours());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;
        }
        catch (SQLException e)
        {
            System.out.println("Erreur : "+e);
            return false;
        }
    }
}
*/