package dsf.checkWord.dto;

import java.util.List;

/**
 * 按标题级别组织成树结构
 * 每个节点下方是本节点的直接下属节点
 * 且直接下属节点必须标题级别小于当前节点
 *
 * @author 董少飞
 * @date 2018/10/13
 */
public interface WordNode {

    /**
     * 获取当前node的直接下属节点
     * @return
     */
    List<WordNode> getChildren();

    /**
     * 获取当前节点的标题级别
     * @return
     */
    int getTitleLevel();

}
