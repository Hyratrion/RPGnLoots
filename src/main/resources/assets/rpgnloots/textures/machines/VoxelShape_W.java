Stream.of(
Block.box(2, 0, 9, 8, 1, 16),
Block.box(3, 10, 1, 13, 11, 15),
Block.box(3, 12, 1, 13, 13, 15),
Block.box(2, 11, 0, 14, 12, 16),
Block.box(10, 13, 8, 11, 14, 9),
Block.box(9, 13, 4, 10, 14, 5),
Block.box(6, 13, 4, 7, 14, 5),
Block.box(8, 13, 3, 9, 14, 4),
Block.box(7, 13, 3, 8, 14, 4),
Block.box(7, 13, 12, 8, 14, 13),
Block.box(8, 13, 12, 9, 14, 13),
Block.box(10, 13, 7, 11, 14, 8),
Block.box(5, 13, 7, 6, 14, 8),
Block.box(5, 13, 8, 6, 14, 9),
Block.box(4, 13, 13, 5, 14, 14),
Block.box(4, 13, 2, 5, 14, 3),
Block.box(11, 13, 2, 12, 14, 3),
Block.box(11, 13, 13, 12, 14, 14),
Block.box(9, 13, 11, 10, 14, 12),
Block.box(6, 13, 11, 7, 14, 12),
Block.box(8, 0, 9, 14, 1, 16),
Block.box(8, 1, 10, 13, 2, 15),
Block.box(8, 2, 11, 12, 10, 14),
Block.box(8, 2, 2, 12, 10, 5),
Block.box(8, 1, 1, 13, 2, 6),
Block.box(8, 0, 0, 14, 1, 7),
Block.box(4, 2, 2, 8, 10, 5),
Block.box(3, 1, 1, 8, 2, 6),
Block.box(2, 0, 0, 8, 1, 7),
Block.box(4, 2, 11, 8, 10, 14),
Block.box(3, 1, 10, 8, 2, 15)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();