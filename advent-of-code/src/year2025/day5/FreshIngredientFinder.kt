package year2025.day5

import java.io.File
import java.io.InputStream
import kotlin.math.max
import kotlin.math.min

class FreshIngredientFinder {
    fun findFreshIngredients(filePath: String): Int {
        var root: TreeNode? = null
        var freshIngredients = 0

        val inputStream: InputStream = File(filePath).inputStream()
        val bufferedReader = inputStream.bufferedReader()

        var line = bufferedReader.readLine()
        while (line != null)
        {
            if (line.contains('-')) {
                val min = line.substringBefore("-").toLong()
                val max = line.substringAfter("-").toLong()

                root = addTreeNode(root, TreeNode(min, max, null, null))
            } else if (line.isNotEmpty()) {
                if (isFreshIngredient(root, line.toLong())) {
                    freshIngredients++
                }
            }

            line = bufferedReader.readLine()
        }

        return freshIngredients
    }

    private fun addTreeNode(node: TreeNode?, new: TreeNode): TreeNode {
        if (node == null) {
            return new
        }

        if (new.max > node.max) {
            if (new.min <= node.max) {
                node.min = min(node.min, new.min)
                node.max = max(node.max, new.max)

                return node
            }

            node.right = addTreeNode(node.right, new)
        } else {
            if (new.max >= node.min) {
                node.min = min(node.min, new.min)
                node.max = max(node.max, new.max)

                return node
            }

            node.left = addTreeNode(node.left, new)
        }

        return node
    }

    private fun isFreshIngredient(node: TreeNode?, id: Long): Boolean {
        if (node == null) {
            return false
        }

        if (id >= node.min && id <= node.max) {
            return true
        } else if (id > node.max) {
            return isFreshIngredient(node.right, id)
        } else {
            return isFreshIngredient(node.left, id)
        }
    }

    fun findAmountOfFreshIngredientIds(filePath: String): Long {
        var root: TreeNode? = null

        val inputStream: InputStream = File(filePath).inputStream()
        val bufferedReader = inputStream.bufferedReader()

        var line = bufferedReader.readLine()
        while (line != null)
        {
            if (line.contains('-')) {
                val min = line.substringBefore("-").toLong()
                val max = line.substringAfter("-").toLong()

                root = addTreeNode(root, TreeNode(min, max, null, null))
            } else if (line.isNotEmpty()) {
                break
            }

            line = bufferedReader.readLine()
        }



        var treeSize = getTreeSize(root)
        while (true) {
            if (root == null) {
                return 0
            }

            var consolidatedRoot = TreeNode(root.min, root.max, null, null)

            consolidatedRoot = rebuildTree(consolidatedRoot, root)
            root = consolidatedRoot

            val consolidatedSize = getTreeSize(root)
            if (treeSize == consolidatedSize) {
                break
            }

            treeSize = consolidatedSize
        }

        return addNodeRanges(root)
    }

    private fun getTreeSize(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }

        return 1 + getTreeSize(node.left) + getTreeSize(node.right)
    }

    private fun rebuildTree(root: TreeNode, new: TreeNode?): TreeNode {
        var node = root

        if (new == null) {
            return node
        }

        node = addTreeNode(node, TreeNode(new.min, new.max, null, null))

        node = rebuildTree(node, new.left)
        node = rebuildTree(node, new.right)

        return node
    }

    private fun addNodeRanges(node: TreeNode?): Long {
        if (node == null) {
            return 0
        }

        return node.max - node.min + 1 + addNodeRanges(node.left) + addNodeRanges(node.right)
    }
}