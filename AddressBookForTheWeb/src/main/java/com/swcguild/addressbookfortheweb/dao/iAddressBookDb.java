/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.addressbookfortheweb.dao;

import com.swcguild.addressbookfortheweb.model.Address;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author apprentice
 */
public class iAddressBookDb implements AddressDao {

    private static final String SQL_INSERT_CONTACT
            = "INSERT INTO address (firstName,lastName,street,city,state,zip) "
            + "values (?,?,?,?,?,?)";

    private static final String SQL_DELETE_CONTACT
            = "DELETE FROM address WHERE addressId= ?";

    private static final String SQL_SELECT_CONTACT
            = "SELECT * FROM address WHERE addressId = ?";

    private static final String SQL_UPDATE_CONTACT
            = "UPDATE address SET firstName = ?, lastName = ?, street = ?, city = ?, state = ?, zip = ? WHERE addressId = ?";

    private static final String SQL_SELECT_ALL_CONTACTS
            = "SELECT * FROM address";

    private static final String SQL_GET_SIZE
            = "SELECT COUNT(*) from address";

    private static final String SQL_SEARCH
            = "SELECT * FROM address WHERE ";

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Address[] search(Address address) {

        if (address.getFirst().equals("")
                && address.getFirst().equals("")
                && address.getLast().equals("")
                && address.getCity().equals("")
                && address.getState().equals("")
                && address.getZip().equals("")
                && address.getStreet().equals("")) {
            List<Address> alist = jdbcTemplate.query(SQL_SELECT_ALL_CONTACTS, new AddressMapper());
            return alist.toArray(new Address[0]);
        } else {

        // look into using namedParameterJDBCtemplate
            ArrayList<String> params = new ArrayList<>();
            ArrayList<String> questions = new ArrayList<>();

            if (!address.getFirst().isEmpty()) {
                params.add("firstName=?");
                questions.add(address.getFirst());
            }
            if (!address.getLast().isEmpty()) {
                params.add("lastname=?");
                questions.add(address.getLast());
            }
            if (!address.getState().isEmpty()) {
                params.add("street=?");
                questions.add(address.getStreet());
            }
            if (!address.getCity().isEmpty()) {
                params.add("city=?");
                questions.add(address.getCity());
            }
            if (!address.getState().isEmpty()) {
                params.add("state=?");
                questions.add(address.getState());
            }
            if (!address.getZip().isEmpty()) {
                params.add("zip=?");
                questions.add(address.getZip());
            }

            String[] questionMark = new String[questions.size()];
            String parameters;

            parameters = params.get(0);
            for (int i = 1; i < params.size(); i++) {
                parameters = parameters + " AND " + params.get(i);
            }

            for (int i = 0; i < questions.size(); i++) {
                questionMark[i] = questions.get(i);
            }

            String search = "SELECT * FROM address WHERE " + parameters;
            List<Address> alist = jdbcTemplate.query(search, questionMark, new AddressMapper());
            return alist.toArray(new Address[0]);
        }
    }

    @Override
    public void add(Address address) {
        jdbcTemplate.update(SQL_INSERT_CONTACT,
                address.getFirst(),
                address.getLast(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip());

        address.setAddressId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

    }

    //overload to work with an integer
    public void remove(int indexOfLastName) {
        jdbcTemplate.update(SQL_DELETE_CONTACT, indexOfLastName);

    }

    public Address get(int lastName) {
        try {
            Address nc = jdbcTemplate.queryForObject(SQL_SELECT_CONTACT, new AddressMapper(), lastName);
            return nc;
        } catch (EmptyResultDataAccessException ex) {
            //there were null results
            //wee just want to return null in this cass
            return null;
        }
    }

    @Override
    public Address[] getAll() {
        List<Address> cList = jdbcTemplate.query(SQL_SELECT_ALL_CONTACTS, new AddressMapper());
        return cList.toArray(new Address[0]);

    }

    @Override
    public void update(Address address) {
        jdbcTemplate.update(SQL_UPDATE_CONTACT,
                address.getFirst(),
                address.getLast(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZip(),
                address.getAddressId());

    }

    @Override
    public int size() {
        return jdbcTemplate.queryForObject(SQL_GET_SIZE, Integer.class);
    }

    private static final class AddressMapper implements ParameterizedRowMapper<Address> {

        //used to parse database results into a contact and returns a contact
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address contact = new Address();
            contact.setAddressId(rs.getInt("addressId"));//the column name 
            contact.setFirst(rs.getString("firstname"));
            contact.setLast(rs.getString("lastname"));
            contact.setStreet(rs.getString("street"));
            contact.setCity(rs.getString("city"));
            contact.setState(rs.getString("state"));
            contact.setZip(rs.getString("zip"));

            return contact;
        }
    }

}
