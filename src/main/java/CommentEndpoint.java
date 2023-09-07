//package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.comment.CommentDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

public class CommentEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMENTS_END = "/comments";
    private static final String COMMENTS_RESOURCE_END = "/comments/{commentID}";

    /**
     * Class constructor
     * <p>
     * Creates instance of <code>CommentEndpoint class</code>
     * </p>
     *
     * @param specification Rest assured request specification
     */
    public CommentEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new comment and validates the response has the <code>HttpStatus.CREATED</code> status code
     * <p>
     * Response gotten is extracted as <code>CommentDto variable</code>
     * </p>
     *
     * @param commentDto body payload
     * @return POST response in <code>CommentDto</code> format
     */
    public CommentDto create(CommentDto commentDto) {
        return create(commentDto, HttpStatus.CREATED)
                .extract().as(CommentDto.class);
    }

    /**
     * Creates a new comment and validates the response status code is the expected
     * <p>
     * The <code>create method</code> calls the <code>post method</code> from its parent class
     * </p>
     *
     * @param commentDto body payload
     * @param status     expected http status
     * @return POST response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse create(CommentDto commentDto, HttpStatus status) {
        LOGGER.info("Create new Comment");
        return post(
                this.specification,
                COMMENTS_END,
                commentDto)
                .statusCode(status.getCode());
    }

    /**
     * Updates a comment and validates the has the <code>HttpStatus.OK</code> status code
     * <p>
     * Response gotten is extracted as <code>CommentDto variable</code>
     * </p>
     *
     * @param id         comment's id
     * @param commentDto body payload
     * @return PUT response in <code>CommentDto</code> format
     */
    public CommentDto update(int id, CommentDto commentDto) {
        return update(commentDto, id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * Updates a comment and validates the response status code is the expected
     * <p>
     * The <code>update method</code> calls the <code>put method</code> from its parent class
     * </p>
     *
     * @param commentDto body payload
     * @param id         comment's id
     * @param status     expected http status
     * @return PUT response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse update(CommentDto commentDto, int id, HttpStatus status) {
        LOGGER.info("Update Comment by id [{}]", id);
        return put(
                this.specification,
                COMMENTS_RESOURCE_END,
                commentDto,
                id)
                .statusCode(status.getCode());
    }

    /**
     * Gets comment by ID and validates the response has the <code>HttpStatus.OK</code> status code
     * <p>
     * Response gotten is extracted as <code>CommentDto variable</code>
     * </p>
     *
     * @param id comment's id
     * @return GET response in <code>CommentDto</code> format
     */
    public CommentDto getById(int id) {
        return getById(id, HttpStatus.OK)
                .extract().as(CommentDto.class);
    }

    /**
     * Gets a comment by ID and validates the response status code is the expected
     * <p>
     * The <code>getById method</code> calls the <code>get method</code> from its parent class
     * </p>
     *
     * @param id     comment's id
     * @param status expected http status
     * @return GET response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse getById(int id, HttpStatus status) {
        LOGGER.info("Get Comment by id [{}]", id);
        return get(
                this.specification,
                COMMENTS_RESOURCE_END,
                String.valueOf(id))
                .statusCode(status.getCode());
    }

    /**
     * Gets a list of comments and validates the response has the <code>HttpStatus.OK</code> status code
     *
     * @return List of comments
     */
    public List<CommentDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(CommentDto[].class));
    }

    /**
     * Gets a list of comments and validates the response status code is the expected
     *
     * @param status expected http status
     * @return response in REST Assured <code>ValidatableResponse</code> format
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Comments");
        ValidatableResponse response = get(this.specification, COMMENTS_END);
        response.statusCode(status.getCode());
        return response;
    }


}
