package com.ttrung.supershop.product.controller;

import com.ttrung.supershop.product.domain.Product;

import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.metamodel.object.SnapshotType;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/products")
public class AuditController {

    private final Javers javers;

    @Autowired
    public AuditController(Javers javers) {
        this.javers = javers;
    }

    @GetMapping("/audit")
    public String getProductUpdates() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Product.class);
        jqlQuery.withSnapshotType(SnapshotType.UPDATE);

        List<Change> changes = javers.findChanges(jqlQuery.build());

        return javers.getJsonConverter().toJson(changes);
    }
}