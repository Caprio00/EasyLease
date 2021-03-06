package com.easylease.easylease.model.order;


import java.util.List;

/**
 * Interface that provides the operations that can be performed on the DataBase
 * for the Order Object.
 *
 * @author Antonio Sarro
 * @version 0.2
 * @since 0.1
 */
public interface OrderDao {
  /**
   * Search for an Order based on it's id.
   *
   * @param id of the {@link Order} you are looking for.
   * @return the {@link Order} with that id or null if not present in Database.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if the id is null or equal to "".
   */
  Order retrieveById(String id);

  /**
   * Search for an Order based on Advisor id.
   *
   * @param id of the Advisor.
   * @return a {@link List} of {@link Order}s or null if there are none.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if the id is null or equal to "".
   */
  List<Order> retrieveByAdvisor(String id);

  /**
   *  Search for an Order based on Client id.
   *
   * @param id of the Client.
   * @return a {@link List} of {@link Order}s or null if there are none.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if the id is null or equal to "".
   */
  List<Order> retrieveByClient(String id);

  /**
   *  Search for an Order based on its state.
   *
   * @param state of the Order.
   * @return a {@link List} of {@link Order}s or null if there are none.
   * @since 0.2
   * @version 0.1
   * @throws IllegalArgumentException if the state is null or equal to "".
   */
  List<Order> retrieveByState(String state);

  /**
   * Search for all {@link Order}s in the Database.
   *
   * @return a {@link List} of {@link Order}
   * @since 0.1
   * @version 0.2
   */
  List<Order> retrieveAll();

  /**
   * Updates the {@link Order} that is passed as a parameter in the Database.
   *
   * @param order {@link Order} to update.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if retrieveById(order.getId()) == null.
   */
  void update(Order order) throws IllegalArgumentException;

  /**
   * Inserts the {@link Order} that is passed as a parameter in the Database.
   *
   * @param order {@link Order} to insert.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if retrieveById(order.getId()) != null.
   */
  void insert(Order order) throws IllegalArgumentException;

  /**
   * Deletes the {@link Order} that is passed as a parameter in the Database.
   *
   * @param order {@link Order} to delete.
   * @since 0.1
   * @version 0.2
   * @throws IllegalArgumentException if retrieveById(order.getId()) == null.
   */
  void delete(Order order) throws IllegalArgumentException;
}
