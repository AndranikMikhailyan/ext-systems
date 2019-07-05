package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonCheckDao {

    private static final String SQL_REQUEST =
            "select * from cr_address_person ap\n" +
                    "inner join cr_person p on p.person_id = ap.person_id\n" +
                    "inner join cr_address a on a.address_id = ap.address_id\n" +
                    "where\n" +
                    "upper(p.sur_name) = upper(?)\n" +
                    "and upper(p.given_name) = upper(?) \n" +
                    "and upper(p.patronymic)= upper(?)\n" +
                    "and p.date_of_birth = ?\n" +
                    "and a.street_code = ?\n" +
                    "and upper(a.building) = upper(?) \n" +
                    "and upper(a.extension) = upper(?) \n" +
                    "and upper(apartment) = upper(?)\n";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(SQL_REQUEST)) {

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                response.setRegistered(true);
                response.setTemporal(rs.getBoolean("temporal"));
            }
        } catch (SQLException ex) {
            throw new PersonCheckException(ex);
        }

        return response;
    }

    private Connection getConnection() {
        return null;
    }
}
