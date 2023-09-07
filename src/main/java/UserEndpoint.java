//package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    /**
     * Class constructor
     * <p>
     * Creates instance of <code>UserEndpoint class</code>
     * </p>
     *
     * @param specification Rest assured request specification
     */
    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new user and validates the response has the <code>HttpStatus.CREATED</code> status code
     *
     * @param userDto body payload
     * @return POST response in <code>UserDto</code> format
     */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
                .extract().as(UserDto.class);
    }

    /**
     * Creates a new user and validates the response status code is the expected
     * <p>
     * The <code>create method</code> calls the <code>post method</code> from its parent class
     * </p>
     *
     * @param userDto body payload
     * @param status  expected http status
     * @return POST response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
                this.specification,
                USERS_END,
                userDto)
                .statusCode(status.getCode());
    }

    /**
     * Updates an existing user and validates the response has the <code>HttpStatus.OK</code> status code
     *
     * @param id      user's ID
     * @param userDto body payload
     * @return PUT response in <code>UserDto</code> format
     */
    public UserDto update(int id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * Updates an existing user and validates the response status code is the expected
     * <p>
     * The <code>create method</code> calls the <code>put method</code> from its parent class
     * </p>
     *
     * @param userDto body payload
     * @param id      user's ID
     * @param status  expected http status
     * @return PUT response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse update(UserDto userDto, int id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
                this.specification,
                USERS_RESOURCE_END,
                userDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Gets user by ID and validates the response has the <code>HttpStatus.OK</code> status code
     *
     * @param id user's ID
     * @return GET response in <code>UserDto</code> format
     */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
                .extract().as(UserDto.class);
    }

    /**
     * Gets a user by ID and validates the response status code is the expected
     * <p>
     * The <code>getById method</code> calls the <code>get method</code> from its parent class
     * </p>
     *
     * @param id     user's ID
     * @param status expected http status
     * @return GET response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
                this.specification,
                USERS_RESOURCE_END,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Gets a list of users and validates the response has the <code>HttpStatus.OK</code> status code
     *
     * @return List of users
     */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
     * Gets a list of users and validates the response status code is the expected
     *
     * @param status expected http status
     * @return Get response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }

}
