package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberName(String memberName);

}
