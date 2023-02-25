package com.gxdxx.programadmin.repository;

import com.gxdxx.programadmin.entity.Member;
import com.gxdxx.programadmin.entity.Position;
import com.gxdxx.programadmin.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByMemberName(String memberName);

    Page<Member> findByRole(Role role, Pageable pageable);

    ArrayList<Member> findByPosition_Id(Long positionId);

}
