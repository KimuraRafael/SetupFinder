package com.dev.kimura.SetupFinder.Repository;

import com.dev.kimura.SetupFinder.Model.SetupItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SetupItemRepository extends JpaRepository<SetupItemModel, Long> {

}
