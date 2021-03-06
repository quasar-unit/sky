package com.sky.server.domain.custom.impl;

import com.mysema.query.jpa.impl.JPAQuery;
import com.sky.commons.domain.QWork;
import com.sky.commons.domain.Work;
import com.sky.server.domain.custom.WorkRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

/**
 * Created by jcooky on 2014. 8. 4..
 */
public class WorkRepositoryImpl implements WorkRepositoryCustom {

  @Autowired
  private EntityManager em;

  @Override
  public Work findReadyWork() {

    QWork work = new QWork("work");

    return new JPAQuery(em)
        .from(work)
        .where(work.finished.eq(false))
        .orderBy(work.createTime.asc())
        .singleResult(work);
  }

  @Override
  public Work findLastWork() {
    QWork work = new QWork("work");

    return new JPAQuery(em)
        .from(work)
        .where(work.worker.isNotNull())
        .orderBy(work.createTime.desc())
        .singleResult(work);
  }

}
