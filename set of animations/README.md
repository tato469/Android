Set of Animations
===========================

I put here some usefull animations.
For use it you can cpy in your res folder and in your activity class call the overridePendingTransition(int id, int id);


You should use it after a finish() or startActivity(Intent)

example:
	startActivity(intent);
	overridePendingTransition(R.anim.push_left_in,  R.anim.not_move_out);

or:
	finish();
	overridePendingTransition(R.anim.push_left_in,  R.anim.not_move_out);

Note:
In a fragent you can use: getActivity().overridePendingTransition(R.anim.push_left_in,  R.anim.not_move_out);