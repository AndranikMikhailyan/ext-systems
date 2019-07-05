package edu.javacourse.city.dao;

import edu.javacourse.city.domain.PersonRequest;
import edu.javacourse.city.domain.PersonResponse;
import edu.javacourse.city.exception.PersonCheckException;

import java.sql.*;

public class PersonCheckDao {

    private static final String SQL_REQUEST =
            "select * from cr_address_person ap " +
                    "inner join cr_person p on p.person_id = ap.person_id " +
                    "inner join cr_address a on a.address_id = ap.address_id " +
                    "where " +
                    "CURRENT_DATE >= ap.start_date and (CURRENT_DATE <= ap.end_date or ap.end_date is null) " +
                    "and upper(p.sur_name) = upper(?) " +
                    "and upper(p.given_name) = upper(?) " +
                    "and upper(p.patronymic)= upper(?) " +
                    "and p.date_of_birth = ? " +
                    "and a.street_code = ? " +
                    "and upper(a.building) = upper(?) ";

    public PersonResponse checkPerson(PersonRequest request) throws PersonCheckException {
        PersonResponse response = new PersonResponse();

        String sql = SQL_REQUEST;
        if (request.getExtension() != null) {
            sql += "and upper(a.extension) = upper(?) ";
        } else {
            sql += "and a.extension is null ";
        }
        if (request.getApartment() != null) {
            sql += "and upper(a.apartment) = upper(?) ";
        } else {
            sql += "and a.apartment is null ";
        }

        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            int count = 1;
            st.setString(count++, request.getSurName());
            st.setString(count++, request.getGivenName());
            st.setString(count++, request.getPatronymic());
            st.setDate(count++, java.sql.Date.valueOf(request.getDateOfBirth()));
            st.setInt(count++, request.getStreetCode());
            st.setString(count++, request.getBuilding());
            if (request.getExtension() != null) {
                st.setString(count++, request.getExtension());
            }
            if (request.getApartment() != null) {
                st.setString(count++, request.getApartment());
            }

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

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/city_register",
                "postgres", "password");
    }
}
