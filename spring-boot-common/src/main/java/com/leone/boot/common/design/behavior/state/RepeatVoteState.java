package com.leone.boot.common.design.behavior.state;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-28
 **/
public class RepeatVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 重复投票，暂时不做处理
        System.out.println("请不要重复投票");
    }

}