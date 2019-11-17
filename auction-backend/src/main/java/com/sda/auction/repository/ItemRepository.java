package com.sda.auction.repository;

import com.sda.auction.model.Item;
import com.sda.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Halip on 10.11.2019.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
